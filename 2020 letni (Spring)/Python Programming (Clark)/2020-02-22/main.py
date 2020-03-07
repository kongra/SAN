def distance(x, y):
  return abs(x - y)

# Ex 1. Write a procedure
#       celsius2fahrenheit
def celsius2fahrenheit(c):
  return (c * 1.8) + 32

print(celsius2fahrenheit(50))

# Ex. 2. Write a procedure that
# computes the area of a circle
# with the given radius.

# Ex. 3. Write a procedure that
# computes the area of a rectangle
def rectArea(a, b):
  return a * b

# Ex. 4. Write a procedure that
# computes the area of a triangle
def triangleArea(a, h):
  return 0.5 * a * h

# Ex. 5. Write a procedure that
# computes the area of a square
# Hint: treat a square as a special
# case of a rectangle
def squareArea(a):
  return rectArea(a, a)

def absoluteValue(x):
  if x < 0:
    return -x
  else:
    return x

print(absoluteValue(7))
print(absoluteValue(-7))
print(absoluteValue(0))

# Ex. 6. Implement a factorial
#        function
def fact(n):
  if n == 0:
    return 1
  else:
    return n * fact(n-1)

# Ex. 7.
def welcome(name):
  return "Welcome " + str(name) + "!!!"
