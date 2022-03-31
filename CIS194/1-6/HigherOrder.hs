import Data.List

fun1 :: [Integer] -> Integer
fun1 [] = 1
fun1 (x:xs)
  | even x    = (x - 2) * fun1 xs
  | otherwise = fun1 xs

-- product 乘积
-- even 偶数返回true

fun1' :: [Integer] -> Integer
fun1' =  product . map (\x -> x - 2) . filter even

fun1'' :: [Integer] -> Integer
fun1'' =  foldl (*) 1 . map (\x -> x - 2) . filter even

-- take 取无限列表的前n个
-- iterate 创建一个infinite list 以递归的方式
-- takeWhile 取列表符合条件的部分

fun2 :: Integer -> Integer
fun2 1 = 0
fun2 n 
    | even n = n + fun2 (n `div` 2)
    | otherwise = fun2 (3 * n + 1)

-- (/= 1)意为不等于1

fun2' :: Integer -> Integer
fun2' =  sum
        . filter even
        . takeWhile (/= 1)
        .iterate (\n -> if even n then n `div` 2 else 3*n+1)


data Tree a = Leaf
            | Node Integer (Tree a) a (Tree a)
  deriving (Show, Eq)

-- should produce a balanced Tree using @foldr@
-- 对length取2的对数之后向下取整
foldTree :: Eq a => [a] -> Tree a
foldTree xs = balancedInsert start xs
  where start = floor (logBase 2 $ fromIntegral(length xs)::Double)

-- a -> Tree a -> Tree a

balancedInsert :: Integer -> [a] -> Tree a
-- balancedInsert _ _ _ = Leaf
balancedInsert start xs = 
  Node start (Leaf) (last xs) (Leaf)

-- balancedInsert _ x (Node n left y right)
--   | right == Leaf = Node n left y (Node (n-1) Leaf x Leaf)
--   | otherwise = Node n (Node (n-1) Leaf x Leaf) y right


xor :: [Bool] -> Bool 
xor = not . even . length . filter (\n -> if n==True then True else False)

map' :: (a -> b) -> [a] -> [b]
map' f = foldr (\x y -> f x : y) []

sieveSundaram :: Integer -> [Integer]
sieveSundaram n = map ((+1) . (*2)) $ [1..n] \\ sieve
  where sieve = map (\(i, j) -> i + j + 2*i*j)
                . filter (\(i, j) -> i + j + 2*i*j <= n)
                $ cartProd [1..n] [1..n]

cartProd :: [a] -> [b] -> [(a, b)]
cartProd xs ys = [(x,y) | x <- xs, y <- ys]

main    =   do
            print(xor [False, True, False])



