setwd("~/Pobrane/2021-11-06")
rm(list = ls())

library(data.table)
library(purrr)

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

flights14[length(flights14$month)]
flights14[1:10]

# Odp. a.
flights14[origin != "JFK"]

# Odp. b.
flights14[month == max(month)]

# Odp. c.
flights14[dest %like% "DF" | dest %like% "BT"]

flights14[origin != "JFK", origin] %>% typeof()

flights14[origin != "JFK", .(origin)] %>% View()
flights14[origin != "JFK", .(origin, dest, distance)]

# .()
# list()
flights14[origin != "JFK", list(origin, dest, distance)]

sel <- function(dt, colname) {
  dt[[colname]]
}

flights14[origin != "JFK"] %>% sel("origin") %>% unique()

# Zadanie 2. Znajdź maksymalną wartość dnia dla origin różnego od JFK
flights14[origin != "JFK",
          .(`Min Day` = min(day),
            `Max Day` = max(day))] %>% View()

# Zadanie 3. Dodaj kolumnę oznaczającą wartość bezwzględną z dep_delay
flights14[, dep_delay_abs := abs(dep_delay)]

colnames(flights14) %>% View()
setcolorder(flights14, c("year",
                         "month",
                         "day",
                         "dep_delay",
                         "dep_delay_abs",
                         "arr_delay",
                         "carrier",
                         "origin",
                         "dest",
                         "air_time",
                         "distance",
                         "hour"))
View(flights14)

# Zadanie 4. Usuń kolumnę dep_delay_abs
flights14[, dep_delay_abs := NULL]
View(flights14)

# Zadanie 5. Powtórz zadanie 3 i przesuń kolumnę dep_delay_abs za dep_delay
flights14 %>% koR::moveDTprops("dep_delay_abs", "after", "dep_delay")

# Zadanie 6. Proszę poćwiczyć manipulację kolumnami na obietach data.table.
