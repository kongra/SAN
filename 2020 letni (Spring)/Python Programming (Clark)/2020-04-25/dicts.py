# 1. Create an empty dictionary
d1 = dict()

# 2. Create a dictionary with some key-values inside
d2 = {'a' : 1,
      'b' : 2,
      'c' : 3}

d3 = dict([('a', 1),
           ('b', 2),
           ('c', 3)])

d4 = dict([['a', 1],
           ['b', 2],
           ['c', 3]])


for k, v in d2.items():
  print("{0} -> {1}".format(k, v))
