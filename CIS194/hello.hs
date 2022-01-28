doubleList    :: [Integer] -> [Integer]
doubleList  []          =   []
doubleList  (x:[])      =   [x]
doubleList  (x:(y:zs))  =   (2*x):y:doubleList zs

addDigits   ::  [Integer]   ->  Integer
addDigits   []      =   0
addDigits   (x:zs)  =   getDigits x+addDigits zs

getDigits   ::  Integer     ->  Integer
getDigits   n
    |   n < 0     =     0
    |   n < 10    =     n
    |   otherwise    =     mod n 10 + getDigits (div n 10)

isValid     ::  Integer     -> Bool
isValid     n   =   (mod n 10)==0


main = do
    print(doubleList [1,3,8,6])
    print(addDigits [2,3,16,6])
    print(isValid  18)

toDigits    ::  Integer   ->  [Integer]
toDigitsRev ::  Integer   ->  [Integer]

toDigits   n
    |   n <  10 && n > 0   =     [n]
    |   n >= 10            =     toDigits (div n 10) ++ (mod n 10 : [])
    |   otherwise          =     []

toDigitsRev   n
    |   n <  10 && n > 0   =     [n]
    |   n >= 10            =     mod n 10 : toDigitsRev (div n 10)
    |   otherwise          =     []


main    =   do
            print(toDigits 1234)
            print(toDigitsRev 1234)


doubleList    :: [Integer] -> [Integer]
doubleList  []          =   []
doubleList  (x:[])      =   [x]
doubleList  (x:(y:zs))  =   (x):(2*y):doubleList zs

doubleEveryOther :: [Integer] -> [Integer]
doubleEveryOther n
           = reverse (doubleList  (reverse n))

main    =   do
            print(doubleEveryOther [8,7,6,5])


sumDigits   ::  [Integer]   ->  Integer
sumDigits   []      =   0
sumDigits   (x:zs)  =   getDigits x+sumDigits zs

getDigits   ::  Integer     ->  Integer
getDigits   n
    |   n < 0     =     0
    |   n < 10    =     n
    |   otherwise    =     mod n 10 + getDigits (div n 10)

main    =   do
            print(sumDigits [16,7,12,5])


sumDigits   ::  [Integer]   ->  Integer
sumDigits   []      =   0
sumDigits   (x:zs)  =   getDigits x+sumDigits zs

getDigits   ::  Integer     ->  Integer
getDigits   n
    |   n <= 0     =     0
    |   n < 10    =     n
    |   otherwise    =     mod n 10 + getDigits (div n 10)

isValid     ::  Integer     -> Bool
isValid     n   =   (mod (getDigits n) 10)==0

main    =   do
            print(isValid 4012888888881881)

type Peg = String
type Move = (Peg,Peg)
hanoi :: Integer -> Peg -> Peg -> Peg -> [Move]
hanoi   n a b c
    | n==1  =   [(a,b)]
    | otherwise = (getHanoiMiddleResult (n-1) a b c) ++ [(a,b)] ++ (getHanoiMiddleResult (n-1) c a b)

getHanoiMiddleResult :: Integer -> Peg -> Peg -> Peg -> [Move]
getHanoiMiddleResult   n a b c
    |n>1       = (a,c) : getHanoiMiddleResult (n-1) a b c
    |otherwise = [(a,c)]

main    =   do
            print(hanoi 2 "a" "b" "c")






type Peg = String
type Move = (Peg,Peg)
hanoi :: Integer -> Peg -> Peg -> Peg -> [Move]
hanoi   n a b c
    | n==1  =   [(a,b)]
    | otherwise = (getHanoiMiddleResult (n-1) a b c) ++ [(a,b)] ++ (getHanoiMiddleResult (n-1) c a b)

getHanoiMiddleResult :: Integer -> Peg -> Peg -> Peg -> [Move]
getHanoiMiddleResult   n a b c
    |n>1       = (a,c) : getHanoiMiddleResult (n-1) a b c
    |otherwise = [(a,c)]

main    =   do
            print(foldr (+) [1,2,3,4,5])