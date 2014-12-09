module Obliczenia where

data Obliczenie a = Wartość a | Błąd deriving Show;

instance Monad Obliczenie where
  return = Wartość

  Błąd        >>= _ = Błąd
  (Wartość x) >>= f = f x

newtype Dodatni = Dodatni Int deriving Show;

dodatni :: Int -> Obliczenie Dodatni
dodatni x = if x > 0 then return (Dodatni x) else Błąd

f :: Dodatni -> Obliczenie Dodatni
f (Dodatni x) = return (Dodatni (2 * x + 3))

inc1 :: Dodatni -> Obliczenie Dodatni
inc1 (Dodatni x) = return (Dodatni (x + 1))

test :: Int -> Obliczenie Dodatni
test x = (dodatni x) >>= f >>= inc1

test1 x = do
  x1 <- dodatni x
  x2 <- f x1
  x3 <- inc1 x2
  return x3

test3 = do
  putStrLn "Hello World!!!"
  putStrLn "Cześć"
  putStrLn "Witaj"
