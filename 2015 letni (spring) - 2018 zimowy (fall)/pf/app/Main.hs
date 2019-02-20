module Main where

printf :: String -> IO Int
printf s = do
  print s
  return (length s)

main :: IO ()
main = putStrLn "Hello World"

-- import Control.Concurrent (forkIO)
-- import Control.Monad (replicateM_)
-- import System.IO (BufferMode(NoBuffering), hSetBuffering, stdout)

-- main :: IO ()
-- main = do
--   hSetBuffering stdout NoBuffering
--   _ <- forkIO (replicateM_ 100000 (putChar 'A'))
--   replicateM_ 100000 (putChar 'B')


-- f :: Int -> Int
-- f x = 2 * x + 3

-- n :: Int
-- n = 4

-- reciprocal :: Double -> Maybe Double
-- reciprocal 0 = Nothing
-- reciprocal x = Just (1 / x)

-- test1 :: Double -> Double
-- test1 x = let r = reciprocal x in
--   case r of
--     Nothing -> 3
--     Just v  -> v + 3

-- test2 :: Maybe Double
-- test2 = do
--   x <- reciprocal 4
--   y <- reciprocal 0
--   z <- reciprocal 6

--   return (x + y + z)

-- test3 :: Maybe Double -> Maybe Double
-- -- test3 Nothing  = Nothing
-- -- test3 (Just x) = Just (x + 3)
-- -- test3 md = fmap (\x -> x + 3) md
-- -- test3 = fmap (\x -> x + 3)
-- test3 = fmap (+3)

-- main :: IO ()
-- main = do
--   print (fmap test1 [1.0, 2.0, 3.0, 0.0])

-- plus:: Int -> Int -> Int
-- plus x y = if x == 0 then y else plus (dec x) (inc y)

-- plus :: Int -> Int -> Int
-- plus 0 y = y
-- plus x y = plus (dec x) (inc y)

-- add :: Int -> Int -> Int
-- add 0 y = y
-- add x y = inc (add (dec x) y)

-- main :: IO ()
-- main = do
--   print (add 300000000 4)

-- AXIOMS
-- inc :: Int -> Int
-- inc x = x + 1

-- dec :: Int -> Int
-- dec x = x - 1

-- import Control.Concurrent
-- import Control.Monad
-- import System.IO

-- f :: Double -> Double
-- f x = 2 * x + 3

-- fib :: Integer -> Integer
-- fib 0 = 0
-- fib 1 = 1
-- fib n = fib (n - 1) + fib (n - 2)
-- -- fib n = if n == 0 || n == 1
-- --         then n
-- --         else fib (n - 1) + fib (n - 2)

-- fastfib :: Int -> Integer -> Integer -> Integer
-- fastfib 0 a _ = a
-- fastfib n a b = fastfib (n-1) b (a + b)
-- -- fastfib n a b =
-- --   if n == 0
-- --   then a
-- --   else fastfib (n-1) b (a + b)

-- foo :: Int -> Int
-- foo x = x + 4

-- goo :: Int -> (Int -> Int)
-- goo x y = x + y + 1
