{-# LANGUAGE CPP #-}
{-# LANGUAGE DeriveDataTypeable #-}
#if defined(__GLASGOW_HASKELL__) && __GLASGOW_HASKELL__ >= 702
{-# LANGUAGE Trustworthy #-}
#endif
{-# OPTIONS_GHC -W -Wall #-}

module Tree.Search where

type Goal     a = a -> Bool
type Adjacent a = a -> [a]
type Combiner a = [a] -> [a] -> [a]

treeSearch :: [a] -> Goal a -> Adjacent a -> Combiner a -> Maybe a
treeSearch []     _    _   _    = Nothing
treeSearch (x:xs) goal adj comb =
    if goal x
    then Just x
    else treeSearch (comb xs (adj x)) goal adj comb

breadthFirstCombiner :: Combiner a
breadthFirstCombiner states children = states ++ children

depthFirstCombiner :: Combiner a
depthFirstCombiner states children = children ++ states
