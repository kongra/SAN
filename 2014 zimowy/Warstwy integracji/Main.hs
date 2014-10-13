{-# LANGUAGE CPP #-}
#if defined(__GLASGOW_HASKELL__) && __GLASGOW_HASKELL__ >= 702
{-# LANGUAGE Trustworthy #-}
#endif
{-# OPTIONS_GHC -W -Wall #-}

module Main where

data QResult = NoRoots
             | SingleRoot  Double
             | DoubleRoots Double Double
  -- deriving (Show)

instance Show QResult where
  show NoRoots             = "Nie ma pierwiastków"
  show (SingleRoot  x)     = "Jeden pierwiastek " ++ show x
  show (DoubleRoots x1 x2) = "Dwa pierwiastki " ++ show x1 ++ ", " ++ show x2

quadraticRoots :: Double -> Double -> Double -> QResult
quadraticRoots a b c = result delta
    where delta = b ^ (2 :: Int) - 4.0 * a * c
          result d
              | d < 0     = NoRoots
              | d == 0    = SingleRoot ((-b) / (2 * a))
              | otherwise = DoubleRoots
                            ((-b) - sqrt delta / (2 * a))
                            ((-b) + sqrt delta / (2 * a))

sumQuadraticRoots :: QResult -> Double
sumQuadraticRoots NoRoots             = 0
sumQuadraticRoots (SingleRoot x)      = x
sumQuadraticRoots (DoubleRoots x1 x2) = x1 + x2

isValidSolution :: QResult -> Bool
isValidSolution NoRoots = False
isValidSolution _       = True

main :: IO ()
main = do
  print (quadraticRoots 1 0 (-4))
  print (quadraticRoots 1 2 3)
  print (quadraticRoots 1 0 0)
