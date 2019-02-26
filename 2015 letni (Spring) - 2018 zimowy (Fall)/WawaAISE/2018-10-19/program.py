def euler1():
  i = 0
  result = 0
  while i < 1000:
    if i % 3 == 0 or i % 5 == 0:
      result += i
    i += 1
  return result

euler1()
