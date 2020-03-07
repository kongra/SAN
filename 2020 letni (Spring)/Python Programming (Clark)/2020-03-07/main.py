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

def inc(n): return n + 1
def dec(n): return n - 1
# From now on, there is no +/-

def plus(x, y): # ITERATION (ITERATIVE PROCESS)
  if x == 0:
    return y
  else:
    return plus(dec(x), inc(y))

def plusLoop(x, y):
  while True:
    if x == 0:
      return y
    else:
      x, y = dec(x), inc(y)
      # x = dec(x)
      # y = inc(y)

def plusLoop1(x, y):
  while x != 0:
    x, y = dec(x), inc(y)

  return y


def plus1(x, y): # RECURSION (RECURSIVE PROCESS)
  if x == 0:
    return y
  else:
    return inc(plus1(dec(x), y))

def euler1():
  i = 1
  s = 0
  while i <= 999:
    if i % 3 == 0 or i % 5 == 0:
      s = s + i

    i = i + 1

  return s

# Assignment 2. https://projecteuler.net/problem=2
# Hint: even number n is n such that
#       n % 2 == 0
