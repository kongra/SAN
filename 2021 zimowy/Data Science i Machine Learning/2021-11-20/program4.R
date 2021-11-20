setwd("~/Arch/Dydaktyka/SAN (SWSPiZ)/Projekty/2021-02-13 Nowy/2021-11-20")
rm(list = ls())

library(data.table)
library(purrr)
library(microbenchmark)
library(pryr)

flights5 <- fread("flights5.csv", stringsAsFactors = TRUE)
str(flights5)

# Zadanie 1. Znajdź ilość rekordów dla różnych wartości origin
# SELECT origin, count(*) FROM flights5 GROUP BY origin;
system.time(flights5[, .N, by = origin])
flights5[, .(Count = .N), by = origin]
.SD

system.time(flights5[, .(Count = nrow(.SD)), by = origin])

# Zadanie 2. Wyznacz liczności rekordów dla
# a. dest
# b. origin i dest
flights5[, .(Count = .N), by = .(origin, dest)]

# c. carrier
# d. origin, dest i carrier
flights5[, .(Count = .N), by = .(origin, dest, carrier)]

# create table flights5 (
#   year INTEGER,
#   month INTEGER,
#   day INTEGER,
#   dep_delay INTEGER,
#   arr_delay INTEGER,
#   carrier TEXT,
#   origin TEXT,
#   dest TEXT,
#   air_time INTEGER,
#   distance INTEGER,
#   hour INTEGER
# );
#
# COPY flights5
# FROM 'flights5.csv'
# DELIMITER ','
# CSV HEADER;

# Zadanie 3. Dla każdego origin i dest wyznacz średnią wartość arr_delay
system.time(flights5[, .(Mean = mean(arr_delay)), by = .(origin, dest)])
# SELECT origin, dest, avg(arr_delay) FROM flights5 GROUP BY origin, dest;

# Zadanie 4. Dla każdego origin i dest wyznacz medianę arr_delay
system.time(flights5[, .(Median = median(arr_delay)), by = .(origin, dest)])
# SELECT origin, dest, percentile_cont(0.5) WITHIN GROUP (ORDER BY arr_delay)
# FROM flights5 GROUP BY origin, dest;

setkeyv(flights5, c("origin", "dest", "arr_delay"))
