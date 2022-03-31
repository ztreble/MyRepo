import Prelude hiding (mconcat, mempty, mappend, Monoid)

class Monoid m where
    mempty  :: m
    mappend :: m -> m -> m

    mconcat :: [m] -> m
    mconcat = foldr mappend mempty

(<>) :: Monoid m => m -> m -> m
(<>) = mappend

instance Monoid [a] where
  mempty  = []
  mappend = (++)

newtype MySum a = MySum a
  deriving (Eq, Ord, Num, Show)

getSum :: MySum a -> a
getSum (MySum a) = a

instance Num a => Monoid (MySum a) where
  mempty  = MySum 0
  mappend = (+)

newtype MyProduct a = MyProduct a
  deriving (Eq, Ord, Num, Show)

getProduct :: MyProduct a -> a
getProduct (MyProduct a) = a

instance Num a => Monoid (MyProduct a) where
  mempty  = MyProduct 1
  mappend = (*)

lst :: [Integer]
lst = [1,5,8,23,423,99]

prod :: Integer
prod = getProduct . mconcat . map MyProduct $ lst

newtype Size = Size Int
  deriving (Eq, Ord, Show, Num)


main :: IO ()
main    =   do
            print(prod)