# SET = A mutable, unordered collection of unique objects

# 1. Create a set
s0 = set()
s1 = {1, 2, 3, 'a', 'b'}
s2 = set([1, 2, 3, 'a', 'b'])
s3 = set((1, 2, 'a'))
s4 = set('abcdefgh')

# 2. Add things to a set
s1.add(5)

# 3. Remove elements from a set
s1.remove(5)

# 4. Create a procedure to safely remove elements from a given set
def setRemove(s, e):
  if e in s:
    s.remove(e)
    return True
  else:
    return False
