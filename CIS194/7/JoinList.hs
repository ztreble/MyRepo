{-# LANGUAGE FlexibleInstances, TypeSynonymInstances #-}
module JoinList where

import Data.Monoid

import Buffer
import Editor
import Scrabble
import Sized

-- joinlist represent append operations as data

data JoinList m a = Empty
                    | Single m a
                    | Append m (JoinList m a) (JoinList m a)

-- instance Buffer (JoinList (score,Size) String) where
--     toString    = unlines . jlToList
--     fromString 

-------- exercise 1 

tag :: Monoid m => JoinList m a -> m
tag (Single m _) = m
tag (Append m _ _) = m
tag _ = mempty

(+++) :: Monoid m => JoinList m a -> JoinList m a -> JoinList m a
(+++) a b = Append ((tag a) <> (tag b)) a b

-------- exercise 2
-------- 课件给出了一个O（1）的实现

jlToList :: JoinList m a -> [a]
jlToList Empty = []
jlToList (Single _ a) = [a]
jlToList (Append _ l1 l2) = jlToList l1 ++ jlToList l2

(!!?) :: [a] -> Int -> Maybe a
[]     !!? _         = Nothing
_      !!? i | i < 0 = Nothing
(x:xs) !!? 0         = Just x
(x:xs) !!? i         = xs !!? (i-1)

-----   2.1

indexJ :: (Sized b, Monoid b) => Int -> JoinList b a -> Maybe a
indexJ index (Single _ a)
  | index == 0 = Just a
  | otherwise  = Nothing
indexJ index (Append m l1 l2)
  | index < 0 || index > size0 = Nothing
  | index < size1              = indexJ index l1
  | otherwise                  = indexJ (index - size1) l2
    where size0 = getSize . size $ m
          size1 = getSize . size . tag $ l1
indexJ _ _ = Nothing

--- 2.2 
--- @意为as

dropJ :: (Sized b, Monoid b) => Int -> JoinList b a -> JoinList b a
dropJ n l1@(Single _ _)
  | n <= 0 = l1
dropJ n l@(Append m l1 l2)
  | n >= size0 = Empty
  | n >= size1 = dropJ (n-size1) l2
  | n > 0 = dropJ n l1 +++ l2
  | otherwise  = l
    where size0 = getSize . size $ m
          size1 = getSize . size . tag $ l1
dropJ _ _ = Empty

--- 2.3

takeJ :: (Sized b, Monoid b) => Int -> JoinList b a -> JoinList b a
takeJ n l1@(Single _ _)
  | n > 0 = l1
takeJ n l@(Append m l1 l2)
  | n >= size0 = l
  | n >= size1 = l1 +++ takeJ (n-size1) l2
  | n > 0 = takeJ n l1
    where size0 = getSize . size $ m
          size1 = getSize . size . tag $ l1
takeJ _ _ = Empty



--- 2.4
scoreLine :: String -> JoinList Score String
scoreLine str = Single (scoreString str) str


a = Append (Size 3)
      (Append (Size 2)
        (Single (Size 1) "hi")
        (Single (Size 1) "bye")
      )
     (Single (Size 1) "tschau")

b = Single (Size 1) "blub"

c = Append (Size 2)
      (Single (Size 1) "hi")
      (Single (Size 1) "bye")