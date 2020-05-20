# Ex. 1. Let's create a procedure that filters out the elements of a list
# (sequence) keeping only these that fulfill a given predicate (Boolean
# procedure)
def filter(pred, lst):
  results = []
  for e in lst:
    if pred(e):
      results.append(e)

  return results

l1 = range(20)

def isEven(n): return n % 2 == 0
l2 = filter(isEven, l1)
l2 = filter(lambda n: n % 2 == 0, l1)

# Ex. 2. Let's create a version of filtering that works with generators
def filterG(pred, seq):
  for e in seq:
    if pred(e):
      yield e

g2 = filterG(isEven, l1)

# Ex. 3. Create a mechanism to infinitely generate values like in the pattern:
#        start, f(start), f(f(start)), f(f(f(start))), ...
def iter(f, start):
  value = start
  while True:
    yield value
    value = f(value)

def inc(n): return n+1

def makeN():
  return iter(inc, 0)

def makeEvenN():
  return filterG(isEven, makeN())

en1 = makeEvenN()

# Ex. 4. Create a procedure that returns the first element in the sequence that
#        matches the given predicate
def findFirst(pred, lst, default=None):
  for e in lst:
    if pred(e):
      return e

  return default

print findFirst(lambda n: n > 1000, makeEvenN())
