module Main where

import qualified Data.Map.Strict as Map
import Control.Concurrent.STM

type ID     = Int
type Login  = String
type Passwd = String
data User   = User { id     :: !ID
                   , login  :: !Login
                   , passwd :: !Passwd } deriving Show;

type TUsers      = TVar (Map.Map ID User)
type TUserLogins = TVar (Map.Map Login User)

data Env = Env {
    idseq  :: !(TVar ID)
  , users  :: !TUsers
  , logins :: !TUserLogins }

createEnv :: STM Env
createEnv = do
  ids <- newTVar 0
  us  <- newTVar Map.empty
  ls  <- newTVar Map.empty
  return (Env ids us ls)

register :: Env -> Login -> Passwd -> STM (Maybe User)
register env login pass = do
  logins' <- readTVar (logins env)
  case Map.lookup login logins' of
    Nothing -> do
      id <- readTVar (idseq env)
      let newId = id + 1
      writeTVar (idseq env) newId
      let user = User newId login pass
      modifyTVar' (users  env) (Map.insert newId user)
      modifyTVar' (logins env) (Map.insert login user)
      return (Just user)

    Just _  -> return Nothing

main :: IO ()
main = do
  env   <- atomically createEnv
  mapM_ (\i -> atomically (register env "Jan" "12345")) [1 .. 10000]
  -- user1 <- atomically (register env "Jan" "12345")
  -- user2 <- atomically (register env "Jan" "12345")
  -- putStrLn (show user1)
  -- putStrLn (show user2)
