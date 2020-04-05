double :: Integer -> Integer
double x = x + x
quadruple x = double(double x)


factorial n = product [1..n]
average ns = sum ns `div` length ns

fac 0 = 1
fac n = n * fac(n-1)
