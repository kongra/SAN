#!/usr/bin/python
# -*- coding: utf-8 -*-

import psycopg2

with psycopg2.connect(database="MAAS", user="scott", password="tiger") as conn:
  with conn.cursor() as cur:
    cur.execute("SELECT * FROM users;")
    print cur.fetchone()


# def mapuj(f, coll):
#   result = []
#   for e in coll:
#     result.append(f(e))
#     print "aaa"

#   return result

# def kwadrat(x): return x ** 2

# print mapuj(kwadrat, [1, 2, 3, 4, 5])


# class Ratio (object):
#   __slots__ = ['__m', '__n']

#   @staticmethod
#   def stat(): print "Działa stat"

#   def __init__(self, m, n):
#     self.__m = m
#     self.__n = n

#   def m(self): return self.__m

#   def n(self): return self.__n


# a = Ratio(4, 5)


# class memoized(dict):
#   def __init__(self, func):
#     self.func = func

#   def __call__(self, *args):
#     return self[args]

#   def __missing__(self, key):
#     result = self[key] = self.func(*key)
#     return result


# @memoized
# def foo(x):
#   print "Działa foo(", x, ")"
#   return x + 4

# 0. ZMIANA ALGORYTMU
# 1. kompilacja
# 2. memoryzacja
# 3. indeksowanie
# 4. późne (odroczone) wartościowanie

# def strangeloop():
#   num = 0
#   while 1:
#     yield num
#     num += 1


# for e in strangeloop():
#   print e

