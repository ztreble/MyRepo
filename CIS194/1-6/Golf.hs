module Golf where

skips :: [a] -> [[a]]
skips lst = [each i lst | i <- [1..length lst]]

-- each不是partial functions，因为它对于任何输入，都是安全的
each :: Int -> [a] -> [a]
each n lst = [lst !! i | i <- [n-1,n-1+n..length lst-1]]

localMaxima :: [Integer] -> [Integer]
localMaxima [] = []
localMaxima lst = concat [maximaHelper i lst | i <- [1..length lst-2]]

maximaHelper :: Int -> [Integer] -> [Integer]
maximaHelper n lst 
    | (lst !! n)>(lst !! (n-1)) && (lst !! n)>(lst !! (n+1)) = [(lst !! n)]
    | otherwise = []
-- 更简单的写法是这样的：
-- localMaxima :: [Integer] -> [Integer]
-- localMaxima (x:y:z:zs)
--   | x < y && y > z = y : localMaxima (y:z:zs)
--   | otherwise      = localMaxima (y:z:zs)
-- localMaxima _ = []


histogram :: [Integer] -> String
histogram xs = unlines (map (line c) [m+1,m..1]) ++ "==========\n0123456789\n"
  where c = count xs
        m = maximum c

line :: [Int] -> Int -> String
line xs n = [if i >= n then '*' else ' ' | i <- xs]

count :: [Integer] -> [Int]
count xs = map (\n -> length $ filter (== n) xs) [0..9]

main    =   do
            print(count [1,4,5,4,6,6,3,4,2,4,9])
