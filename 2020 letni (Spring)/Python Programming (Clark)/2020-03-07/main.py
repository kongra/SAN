def square(x):
  return x * x

def goodEnough(y, x):
  return abs(square(y) - x) < 0.00000001

def avg(y, x):
  return (x + y) / 2

def improve(y, x):
  return avg(y, x / y)

def sqrt(y, x):
  print(y)

  if goodEnough(y, x):
    return y
  else:
    y = improve(y, x)
    return sqrt(y, x)

print(sqrt(1.0, 2.0))

# Assignment 1. Write a procedure that computes a root
# of degree 3 (cube root) for the given number x.
# Hint: Use improvement like: ((x / y^2) + 2y) / 3
