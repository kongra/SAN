setwd("~/Pobrane/2021-11-06")

library(data.table)

flights14 <- fread("flights14.csv")
str(flights14)
typeof(flights14)

l1 <- list()
l1[["abcd"]] <- 1
l1[["xyz"]] <- 2:20
l1[[54]] <- "www"

l1$abcd
l1$xyz
l1[[4]]
l1[["abcd"]]

l2 <- list()
l2[["ID"]]   <- c(1L, 2L, 3L)
l2[["Name"]] <- c("John", "Anna", "Chris")
typeof(l2)
str(l2)
View(l2)

class(l2) <- "data.frame"
View(l2)
l2[["ID"]]   <- c(1L, 2L, 3L)
l2[["Name"]] <- c("John", "Anna", "Chris")

l2 <- data.frame(ID = c(1L, 2L, 3L), Name = c("John", "Anna", "Chris"))
typeof(l2)
class(l2)
str(l2)

l2[["Age"]] <- c(33, 22, 11)
View(l2)
