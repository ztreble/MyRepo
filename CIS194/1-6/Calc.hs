{-# OPTIONS_GHC -Wall -Werror #-}
{-# LANGUAGE TypeSynonymInstances, FlexibleInstances #-}
{-# OPTIONS_GHC -Wno-unused-imports #-}

module Calc where

import ExprT ( ExprT(..) )
import Parser ( parseExp )
import StackVM 
import Data.Maybe 
import qualified Data.Map as M

eval :: ExprT -> Integer 
eval (ExprT.Lit n) = n
eval (ExprT.Add a b) = eval a + eval b
eval (ExprT.Mul a b) = eval a * eval b

-- parseExp :: (Integer -> a) -> (a -> a -> a) -> (a -> a -> a) -> String -> Maybe a
evalStr :: String -> Maybe Integer 
evalStr = fmap eval . parseExp ExprT.Lit ExprT.Add ExprT.Mul

class Expr a where
    lit :: Integer -> a
    add :: a -> a -> a
    mul :: a -> a -> a

instance Expr ExprT where
    lit = ExprT.Lit
    add = ExprT.Add
    mul = ExprT.Mul

-- 通过一个实例方法来实现约束
reify :: ExprT -> ExprT 
reify = id

instance Expr Integer where
  lit = id
  add = (+)
  mul = (*)

instance Expr Bool where
  lit x
    | x <= 0    = False
    | otherwise = True
  add = (||)
  mul = (&&)

newtype MinMax = MinMax Integer deriving (Eq, Show)

instance Expr MinMax where
  lit = MinMax
  add (MinMax x) (MinMax y)= MinMax (max x y)
  mul (MinMax x) (MinMax y)= MinMax (min x y)


newtype Mod7 = Mod7 Integer deriving (Eq, Show)

instance Expr Mod7 where
  lit x = Mod7 (x `mod` 7)
  add (Mod7 x) (Mod7 y)= Mod7 ((x + y) `mod` 7)
  mul (Mod7 x) (Mod7 y)= Mod7 ((x * y) `mod` 7)

testExp :: Expr a => Maybe a
testExp = parseExp lit add mul "(3 * -4) + 5"

testInteger :: Maybe Integer
testInteger = testExp

testBool :: Maybe Bool
testBool = testExp

testMM :: Maybe MinMax
testMM = testExp

testSat :: Maybe Mod7
testSat = testExp

instance Expr StackVM.Program where
  lit i = [StackVM.PushI i]
  add a b = a ++ b ++ [StackVM.Add]
  mul a b = a ++ b ++ [StackVM.Mul]

testProg :: Maybe StackVM.Program
testProg = testExp

compile2 :: String -> Either String StackVal
compile2 = stackVM . fromMaybe [] . compile

compile :: String -> Maybe Program
compile = parseExp lit add mul


main :: IO ()
main    =   do
            print(reify $ mul  (add (lit 2) (lit 3)) (lit 4))