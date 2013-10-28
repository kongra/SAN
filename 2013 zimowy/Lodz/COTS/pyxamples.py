#!/usr/bin/python
# -*- coding: utf-8 -*-

def square(x):
  return x * x

def mymap(f, coll):
  result = []
  for e in coll:
    result.append(f(e))

  return result

def plus(x, y):
  return x + y

def myreduce(f, coll):
  result = coll[0]
  for e in coll[1:]:
    result = f(result, e)

  return result

# TECHNIKI POZWALAJĄCE NA PRZYSPIESZANIE PROGRAMÓW KOMPUTEROWYCH:
# * KOMPILACJA
# * INDEKSACJA
# * MEMORYZACJA
# * PÓŹNA EWALUACJA

def naturals():
  value = 0
  while 1:
    yield value
    value += 1

def take(n, coll):
  count = 0
  for e in coll:
    if count == n:
      break

    yield e
    count += 1

