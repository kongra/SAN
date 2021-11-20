setwd("~/Arch/Dydaktyka/SAN (SWSPiZ)/Projekty/2021-02-13 Nowy/2021-11-20")
rm(list = ls())

library(data.table)
library(purrr)
library(microbenchmark)
library(pryr)
library(feather)
library(koR)

catimela(
  flights5 <- fread("flights5.csv", stringsAsFactors = TRUE)
)
str(flights5)

airports5 <- data.table(origin = c("EWR", "JFK", "CHOP"))
airports5[, capacity := c(1000, 2000, 3000)]

str(airports5)

setkey(flights5, "origin")
setkey(airports5, "origin")

merge.data.table(flights5, airports5, by = "origin", all=FALSE)

merge.data.table(flights5, airports5, by = "origin", all.x=TRUE)
merge.data.table(flights5, airports5, by = "origin", all.y=TRUE)

flights5[airports5, nomatch = 0, on = "origin"]
airports5[flights5][is.na(capacity)]

merge.data.table(flights5, airports5, by = "origin", all=TRUE)

write_feather(flights5, "flights5.feather")

catimela(
  flights5 <- read_feather("flights5.feather") %>% as.data.table()
)

saveRDS(flights5, file = "flights5.rds")

catimela(
  flights5 <- readRDS("flights5.rds")
)
