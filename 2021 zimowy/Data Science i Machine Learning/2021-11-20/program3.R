setwd("~/Arch/Dydaktyka/SAN (SWSPiZ)/Projekty/2021-02-13 Nowy/2021-11-20")
rm(list = ls())

library(data.table)
library(purrr)
library(microbenchmark)
library(pryr)

flights14 <- fread("flights14.csv", stringsAsFactors = TRUE)
str(flights14)

flights14[, carrier] %>% unique()
flights14[, origin] %>% unique()
flights14[, dest] %>% unique()

flights14[origin == "JFK"]

microbenchmark(flights14[origin == "JFK"])
object_size(flights14)

flights14[, carrier := as.factor(carrier)]
flights14[, origin  := as.factor(origin )]
flights14[, dest    := as.factor(dest   )]

flights14[1, year] %>% pryr::bits()

setkey(flights14, "origin")
levels(flights14[, carrier])

