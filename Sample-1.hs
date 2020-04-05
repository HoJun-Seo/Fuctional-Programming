module Sample where
import Prelude hiding (or, max, length,  map)
import Data.Char hiding (isDigit, toUpper)

-- f :: b -> b
f :: Int -> Int
f x = x

g x = x + 9

-- Currying ------------------

addInt x y = x + y

add x y = add1 x y
add1 a = add0 a   -- the return values is "(+) a"
add0 = (+)        -- the return value is "(+)"

-- data Int2Int = In (Int -> Int)
-- add00 :: Int2Int
-- add00 = In (+ 3)
--
-- showIn :: Int2Int -> String
-- showIn (In a) = show a

-- The Booleans  ------------------------------

exOr x y = (x || y) && not (x && y)

-- pattern matching

or x True       = True
or True x       = True
or False False  = False

equal_no 0 0     = True
equal_no x 0     = False
equal_no 0 x     = False
equal_no x y     = equal_no (x-1) (y-1)

myNot True  = False
myNot False = True

exOr1 True  x = not x
exOr1 False x = x


-- Integers and guards -------------------------

threeEqual m n p = (m==n) && (n==p)

max x y
  | x >= y      = x
  | otherwise   = y

-- max using    if ... then ... else ...

max' x y
  = if x >= y then x else y

maxThree x y z
  | x >= y && x >= z    = x
  | y >= z              = y
  | otherwise           = z

-- use max' function 

maxThree' x y z = (x `max` y) `max` z


-- Characters. -------------------------------

-- Converting lower-case letters to upper-case
 
toUpper :: Char -> Char
toUpper ch = chr (ord ch + offset)

offset = ord 'A' - ord 'a'

-- check whether a character is a digit 

isDigit :: Char -> Bool
isDigit ch = ('0' <= ch) && (ch <= '9')


-- Some syntax. -----------------------------------

-- two definitions on one line, separated by a `;'.

answer = 42 ;   facSix = 720 

-- functions defined using other functions

middleNumber x y z
  | between y x z      = x
  | between x y z      = y
  | otherwise          = z

-- replace between by a proper definition
between ::  Int -> Int -> Int -> Bool
between = between


-- Primitive recursion  -------------------------------------

fac :: Int -> Int
fac n
  | n==0        = 1
  | n>0         = fac (n-1) * n
  | otherwise   = error "fac only defined on natural numbers"

power2 n
  | n==0        = 1
  | n>0         = 2 * power2 (n-1)

-- 0! + 1! + ... n!.

sumFacs n
  | n==0        = 1
  | n>0         = sumFacs (n-1) + fac n  

-- quicksort 

quicksort []      = []
quicksort (x:xs)  = quicksort [y | y <- xs, y < x]
                    ++ [x]
                    ++ quicksort [y | y <- xs, y >= x]

-- f 0 + f 1 + ... f n

sumFun f n
  | n==0        = f 0
  | n>0         = sumFun f (n-1) + f n  


-- The Fibonacci numbers 0, 1, 1, 2, 3, 5, ..., u, v, u+v, ...

fib n 
  | n==0        = 0
  | n==1        = 1
  | n>1         = fib (n-2) + fib (n-1)

---List comprehension -------------------

ex = [2, 4, 7]
listTest1 = [2*n | n <- ex]
listTest2 = [even n | n <- ex]

addPairs :: [(Int, Int)] -> [Int]
addPairs pairList = [m+n | (m, n) <- pairList]

addOrdPairs pairList = [m + n | (m, n) <- pairList, m < n]

digits st = [c | c <- st, isDigit c]

-- polymorhphic functions --------------------

length :: [a] -> Int
length [] = 0
length (x:xs) = 1 + length xs

-- overloaded function 

-- (n, m) == (p, q) = (n == p) && (m == q)

-- functions as parameters --------------------

map f [] = []
map f (x:xs) = (f x) : (map f xs)

map' f xs = [f x | x <- xs]

ap f x = f x
-- Local definitions ------------------------

test1 = (>100) . square
        where
        square x = x * x

test2 :: Bool -> String
test2 =  converToString . not
         where
         converToString x = case x of
                            True -> "Yes"
                            False -> "No"

-- function composition ---------------------------

-- comp = (+) 2 . fst
comp = (+) 2 . snd

modOdd = odd . (mod 10)
-- modOdd x = odd (mod 10 x)


doubleSum = sum . map (*2) 

maxTuple (x, y) = if  x > y then x else y

test3 = sum (map (square . maxTuple) [(3, 2), (8, 2), (0, 1)])
square x = x * x

-- test -------

approx :: Bool -> Bool
approx x = not (approx x)

-- k :: Int -> Int
loop x = loop x

k x y = x
h x y = y
