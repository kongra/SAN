# * Numbers are the first-class objects in Python
def foo1(n):
  return 2 * n + 3

# Ex. 1. Write down a procedure that computes a sum of a, a+1, a+2, ..., b
def sumInts(a, b):
  if a > b:
    return 0
  else:
    return a + sumInts(a+1, b)

def sumInts3(a, b):
  result = 0
  while True:
    if a > b:
      return result
    else:
      a, result = a+1, result+a

# sumInts(1, 5)
# 1 + sumInts(2, 5)
# 1 + (2 + sumInts(3, 5))
# 1 + (2 + (3 + sumInts(4, 5)))
# 1 + (2 + (3 + (4 + sumInts(5, 5))))
# 1 + (2 + (3 + (4 + (5 + sumInts(6, 5)))))
# 1 + (2 + (3 + (4 + (5 + 0))))
# 1 + (2 + (3 + (4 + 5)))
# 1 + (2 + (3 + 9))
# 1 + (2 + 12)
# 1 + 14
# 15

# Ex. 2. Write down a procedure that computes a sum of squares of a, a+1, a+2, ..., b
#        a**2 + (a+1)**2 + (a+2)**2, ..., b**2
def square(n):
  return n ** 2

def sumSquares(a, b):
  if a > b:
    return 0
  else:
    return square(a) + sumSquares(a+1, b)

# Ex. 3. Write down a procedure that computes a sum of qubes of a, a+4, a+8, ..., b
#        a**3 + (a+4)**3 + (a+8)**3, ..., b**3
def qube(n):
  return n ** 3

def sumQubes(a, b):
  if a > b:
    return 0
  else:
    return qube(a) + sumQubes(a+4, b)

# Let's write down a generalization
# def sumCore(term, step, a, b):
#   if a > b:
#     return 0
#   else:
#     return term(a) + sumCore(term, step, step(a), b)

def sumCore(term, step, a, b):
  result = 0
  while True:
    if a > b:
      return result
    else:
      a, result = step(a), result + term(a)

def sumInts1(a, b):
  def term(n): return n
  def step(n): return n+1
  return sumCore(term, step, a, b)

def sumInts2(a, b):
  return sumCore(lambda n: n,   # term
                 lambda n: n+1, # step
                 a, b)

def sumInts4(a, b):
  term = lambda n: n
  step = lambda n: n+1
  return sumCore(term, step, a, b)

def sumSquares1(a, b):
  def step(n): return n+1
  return sumCore(square, step, a, b)

def sumQubes1(a, b):
  def step(n): return n+4
  return sumCore(qube, step, a, b)

# Ex. 4. Implement a counter functionality in the system.
# c1 = 0
# def incAndGet():
#   global c1

#   c1 += 1
#   return c1

# def makeCounter(count=[0]):
#   def counter():
#     count[0] += 1
#     return count[0]

#   return counter

# c2 = makeCounter()
# c3 = makeCounter()
# c4 = makeCounter()

# Ex. 5. Create a function that takes a predicate and returns it's negation
# def complement(pred):
#   def negation(x):
#     return not pred(x)

#   return negation

def complement(pred):
  return lambda x: not pred(x)

def isEven(n):
  return n % 2 == 0

isOdd = complement(isEven)
