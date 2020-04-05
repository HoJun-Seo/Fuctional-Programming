import Prelude  hiding(length)

zeroto :: Int -> [Int]
zeroto n = [0..n]

pyTriple n = [(x,y,z) | x<-[2..n], y<-[x+1..n], z<-[y+1..n],
                        x*x + y*y == z*z]

allEqual m n p = (m==n) && (n==p)

data Season = Spring | Summer | Autumn | Winter
              deriving (Eq,Ord,Enum,Show,Read)

data Shape  = Circle Float | Rectangle Float Float
              deriving (Ord,Show,Read)
area :: Shape -> Float
area (Circle r) = pi * r * r
area (Rectangle x y) = x * y

instance Eq Shape where
    Circle x == Circle y  =  x == y
    Rectangle a b == Rectangle x y   = (a == x) && (b == y)

-- data MultiData = Tr Tree | Ls [Int]
--

qsort [] = []
qsort (x:xs) =  qsort [y | y <- xs, y <= x]++ [x] ++ qsort [y | y <- xs, y > x]

from n = n : (from (n+1))

gg = \x -> x + 31

twice f = f . f

twice' f x = f ( f x)

-- id' :: ((->) a) a
id' x = x

-- k :: (->) a ((->) b a)
-- k :: a -> b -> c
k x y = x

g:: (Int -> Bool) -> (Int -> Bool)
g k = k
f h x = (g h x) == True

data Tree = Nil | Node Int Tree Tree
treeToList :: Tree -> [Int]
treeToList Nil = []
treeToList (Node i t1 t2) = treeToList t1 ++ [i] ++ treeToList t2

inOrder :: Tree -> [Int]
inOrder Nil = []
inOrder (Node i t1 t2) = [i] ++ (inOrder t1) ++ (inOrder t2)
tree1 = Node 1 (Node 2 (Node 3 Nil Nil) (Node 4 Nil Nil)) (Node 5 Nil Nil) 

atree = (Node 1 (Node 2 (Node 4 Nil Nil) (Node 5 Nil Nil))  (Node 3 Nil Nil))

-- try testToList atree

cons a [] = [a]
cons a xs = (a:xs)

length :: [a] -> Int
length [] = 0
length (x:xs) = 1 + length xs

g' [] = 0
g' (x:xs) = (twice (+2) x) + (g' xs)

data Week = Sun | Mon | Tues | Wed | Thur | Fri | Sat
work :: Week -> Bool
work Sun = False
work Sat = False
work _   = True

maxList (x:xs) = foldr max x xs

u x = x + 2
u' = \x -> x + 2

map' f xs = foldr (\x y -> f x : y) [] xs

filter' :: (a -> Bool) -> [a] -> [a]
filter' p []                 = []
filter' p (x:xs) | p x       = x : filter' p xs
                 | otherwise = filter' p xs

filter'' p xs = foldr (\x y -> if (p x) then x : y else y) [] xs

add x y = x + y

add2 = \x y -> x + y

add1 x = \y -> x + y

safehead [] = Nothing
safehead xs = Just (head xs)
