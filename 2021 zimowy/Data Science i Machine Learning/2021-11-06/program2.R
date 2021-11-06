setwd("~/Pobrane/2021-11-06")
rm(list = ls())

library(data.table)

flights14 <- fread("flights14.csv")

str(flights14)
typeof(flights14)

# DT[i, j, by]

unique(flights14$origin)
flights14[origin == "JFK" & dest == "LAX"]

typeof(flights14$origin)
str(flights14$origin)
summary(flights14$origin)

flights14$origin == "JFK"
flights14$dest == "LAX"

1:20 == 20
20 == 1:20

flights14$origin == "JFK" & flights14$dest == "LAX"

flights14[flights14$origin == "JFK" & flights14$dest == "LAX"]
flights14[origin == "JFK" & dest == "LAX"]

# Zadanie 1. Napisz program, który znajdzie wszystkie rekordy flights14, które:
# a. Nie mają origin JFK
# b. Były w ostatnim miesiącu w całym zbiorze
# c. Mają dest zawierające "DF" lub "BT"

