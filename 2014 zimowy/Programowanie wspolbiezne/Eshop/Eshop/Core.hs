{-# LANGUAGE CPP #-}
#if defined(__GLASGOW_HASKELL__) && __GLASGOW_HASKELL__ >= 702
{-# LANGUAGE Trustworthy #-}
#endif
{-# OPTIONS_GHC -W -Wall #-}
------------------------------------------------------------------------
-- |
-- Module      : Eshop.Core
-- Copyright   : (c) 2014 Konrad Grzanek
-- License     : BSD-style (see the file LICENSE)
-- Created     : 2015-01-04
-- Maintainer  : kongra@gmail.com
-- Stability   : experimental
-- Portability : requires stm
------------------------------------------------------------------------
module Eshop.Core where

import           Control.Concurrent.STM
import           Control.Monad (when, forM_ )
import qualified Data.HashMap.Strict as Map
import           Data.Hashable (Hashable, hashWithSalt)

type Id = Int

-- SYSTEM

data System =
  System
  {
    systemClients :: !(TVar Clients)
  , systemLogins  :: !(TVar Logins)
  , recentId      :: !(TVar Id)
  }

createSystem :: STM System
createSystem = do
  clients <- newTVar (Clients Map.empty)
  logins  <- newTVar (Logins  Map.empty)
  rid     <- newTVar 0

  return System { systemClients = clients
                , systemLogins  = logins
                , recentId      = rid }

genid :: System -> STM Id
genid System { recentId = recent } = do
  r <- readTVar recent
  when (r == maxBound) (error "Can't generate Id.")
  let new = r + 1
  writeTVar recent new
  return new

-- KLIENT

newtype ClientId = ClientId Id  deriving (Show, Eq)
newtype Login    = Login String deriving (Show, Eq)
newtype Pass     = Pass  String deriving (Show, Eq)

instance Hashable ClientId where
  hashWithSalt salt (ClientId i) = salt `hashWithSalt` i

instance Hashable Login where
  hashWithSalt salt (Login l) = salt `hashWithSalt` l

newtype Clients = Clients (Map.HashMap ClientId Client)
newtype Logins  = Logins  (Map.HashMap Login    Client)

data Client =
  Client
  {
    clientId    :: !ClientId
  , clientLogin :: !Login
  , clientPass  :: !Pass
  }
  deriving Show

instance Eq Client where
  Client { clientId = id1 } == Client { clientId = id2 } = id1 == id2

data Register = EmptyLogin
              | LoginInUse
              | Registered !Client deriving Show

register :: System -> Login -> Pass -> STM Register
register _ (Login "") _  = return EmptyLogin
register sys login pass = do
  used <- loginInUse sys login
  if used
    then return LoginInUse
    else addUser sys login pass

loginInUse :: System -> Login -> STM Bool
loginInUse System { systemLogins = logins } login = do
   Logins ls <- readTVar logins
   case Map.lookup login ls of
     Nothing -> return False
     Just _  -> return True

addUser :: System -> Login -> Pass -> STM Register
addUser sys login pass = do
  nid <- genid sys
  let cid    = ClientId nid
      client = Client { clientId    = cid
                      , clientLogin = login
                      , clientPass  = pass }

  Clients clients <- readTVar (systemClients sys)
  writeTVar (systemClients sys) $! (Clients (Map.insert cid client clients))

  Logins logins <- readTVar (systemLogins sys)
  writeTVar (systemLogins sys) $! (Logins (Map.insert login client logins))

  return (Registered client)

-- PRODUKT

data Product =
  Product
  {
    productId :: !Id
  , productName :: !String
  -- , productCount :: !(TVar Int)
  }
  deriving Show

instance Eq Product where
  Product { productId = id1 } == Product { productId = id2 } = id1 == id2

-- ZAKUP

data Purchase =
  Purchase
  {
    purchaseId         :: !Id
  , purchaseProduct    :: !Product
  , purchaseClient     :: !Client
  , purchaseItemsCount :: !Int
  , purchasePrice      :: !Double
  }
  deriving Show

instance Eq Purchase where
  Purchase { purchaseId = id1 } == Purchase { purchaseId = id2 } = id1 == id2

teścior :: IO ()
teścior = do
  sys <- atomically createSystem
  reg1 <- atomically $ register sys (Login "janek") (Pass "12345")
  reg2 <- atomically $ register sys (Login "janek") (Pass "12345")
  atomically $ registerMillion sys
  putStrLn (show reg1)
  putStrLn (show reg2)

registerMillion :: System -> STM ()
registerMillion sys = do
  forM_ [1 .. 100000] $ \i -> do
    let login = Login ("janek-" ++ show (i :: Int))
        pass  = Pass  "12345"
    register sys login pass
