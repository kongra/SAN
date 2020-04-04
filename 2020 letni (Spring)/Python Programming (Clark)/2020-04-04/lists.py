x = 1
y = 2
txt = "abc"

# 1. Create a sequence of the first
#    5 natural numbers
ns = [0, 1, 2, 3, 4]

# 2. Create a sequence of the first
#    100000 natural numbers
ns1 = range(0, 100000)

# 3. Create a sequence of some textual
#    elements
txts = ['today', 'is', 'sunny']
# Indexes:   0 ,   1 ,      2
t1 = txts[0]
t2 = txts[1]
txts[2] = 'rainy'

# 4. Check if the list contains a
#    specific element
if 'rainy' in txts:
  print 'Liar'

# 5. Pretend that there is no range
#    function and create you own
def myRange(start, end):
  result = []
  i = start
  while i < end:
    result.append(i)
    i += 1

  return result

# 6. Create a function fibSeq that
#    generates a list of the first
#    n elements of the Fibonacci
#    sequence
def fibSeq(n):
  result = []
  a = 0
  b = 1
  i = 0

  while i < n:
    result.append(a)
    a, b = b, a+b
    i += 1

  return result

# 7. Print the first 100 Fibonacci
#    sequence elements
f1 = fibSeq(100)
# print f1

# 8. Print the first 100 Fibonacci
#    sequence elements together with
#    the indexes of the elements
i = 0
for e in f1:
  print i, e
  i += 1

# 9. Use for loop and fibSeq to solve
#    https://projecteuler.net/problem=2
def euler2():
  result = 0
  for e in fibSeq(50):
    if e >= 4000000:
      break

    if e % 2 == 0:
      result += e

  return result

# 10. Create an inifinite series of
#     natural numbers
def brokenNaturals(): ## A CATASTROPHY!!!
  result = []
  i = 0
  while True:
    result.append(i)
    i += 1

  return result

x = 1 + 2 # Eager evaluation
# x => 3

y = 1 + 2 # Lazy evaluation
# y => Promise of computing 1 + 2
# ...
print y + 2

def naturals():
  # print "naturals starting"
  i = 0
  while True:
    # print "naturals yielding", i
    yield i
    # print "naturals woken up"
    i += 1

# 11. Create an infinite Fibonacci sequence
def fibGen():
  a = 0
  b = 1
  while True:
    yield a
    a, b = b, a+b

# 12. Use fibGen to solve Euler 2
def euler2a():
  result = 0
  for e in fibGen():
    if e >= 4000000:
      break

    if e % 2 == 0:
      result += e

  return result

# LIST AND GENERATORS COMPREHENSIONS
# 13. Create a list of squares of the first
#     10 natural numbers
def squares1(n):
  result = []
  i = 0
  while i < n:
    result.append(i ** 2)
    i += 1

  return result

def squares2(n):
  result = []
  for i in xrange(n):
    result.append(i ** 2)

  return result

def squares3(n):
  return [i ** 2 for i in xrange(n)]

def squares4():
  return (i ** 2 for i in naturals())

[i + j for i in xrange(3) for j in xrange(5)]
