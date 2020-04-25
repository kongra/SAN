# 1. Create a generator of a cartesian product of two arguments
# product([1, 2, 3], ['a', 'b', 'c'])
# => [(1, 'a'), (1, 'b'), (1, 'c'),
#     (2, 'a'), (2, 'b'), (2, 'c'),
#     (3, 'a'), (3, 'b'), (3, 'c')]
def product(s1, s2):
  for e1 in s1:
    for e2 in s2:
      yield e1, e2

for x, y in product([1, 2, 3], ['a', 'b', 'c']):
  print("{0} {1}".format(x, y))
