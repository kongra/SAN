# -*- coding: utf-8 -*-

import psycopg2

def read_users():
  users = []
  
  conn = psycopg2.connect(database="MAAS", user="scott", password="tiger")
  cur = conn.cursor()
  cur.execute("select * from users")
  for u in cur:
    users.append(u)

  conn.close()
    
  return users 

