{-# LANGUAGE Trustworthy #-}
{-# OPTIONS_GHC -W -Wall #-}

data FibCount = FibCount {-# UNPACK #-} !Int {-# UNPACK #-} !Int deriving Show

fibcount :: Int -> FibCount
fibcount 0 = FibCount 0 1
fibcount 1 = FibCount 1 1
fibcount n = FibCount (v1 + v2) (c1 + c2)
  where
    FibCount v1 c1 = fibcount (n-1)
    FibCount v2 c2 = fibcount (n-2)

test :: Int -> IO ()
test _ = putStrLn (show (fibcount 37))

main :: IO ()
main = do
  putStrLn (show (fibcount 37))
  putStrLn (show (fibcount 38))
  putStrLn (show (fibcount 39))
