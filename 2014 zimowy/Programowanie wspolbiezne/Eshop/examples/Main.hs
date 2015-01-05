{-# LANGUAGE CPP #-}
#if defined(__GLASGOW_HASKELL__) && __GLASGOW_HASKELL__ >= 702
{-# LANGUAGE Trustworthy #-}
#endif
{-# OPTIONS_GHC -W -Wall #-}

module Main where

import Eshop.Core
import Control.Monad (forM_)
import Control.Concurrent.STM

main :: IO ()
main = do
  -- sys  <- atomically createSystem
  -- reg1 <- atomically $ register sys (Login "janek") (Pass "12345")
  -- reg2 <- atomically $ register sys (Login "janek") (Pass "12345")
  -- atomically $ registerM
  -- putStrLn (show reg1)
  -- putStrLn (show reg2)
  mregister

registerM :: STM ()
registerM = do
  sys <- createSystem
  forM_ [1 .. 1000000] $ \i -> do
    let login = Login ("janek-" ++ show (i :: Int))
        pass  = Pass  "12345"
    register sys login pass

mregister :: IO ()
mregister =
  forM_ [1 .. 1000000] $ \i -> do
    sys <- atomically createSystem
    let login = Login ("janek-" ++ show (i :: Int))
        pass  = Pass  "12345"
    atomically $ register sys login pass
