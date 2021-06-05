setwd("~/Pobrane/ragent")

library(data.table)
library(koR)

rm(offers4)
catimela(offers4 <- fread("offers4.csv"))
setnames(offers4,
         c("V1", "V2", "V3", "V4"),
         c("rooms", "price", "area", "oneMeterPrice"))
str(offers4)

offers4[is.na(rooms)]
offers4[is.nan(rooms)]

offers3 <- fread("offers3.csv")
setnames(offers3,
         c("V1", "V2", "V3", "V4"),
         c("rooms", "price", "area", "oneMeterPrice"))

catimela(m1 <- lm(formula = price~area, data = offers3))
summary(m1)

m1$coefficients[2]
# price = 4720 * area - 36582

makePricePrediction <- function (area) {
  dt1 <- data.table(area = area)
  predict(m1, dt1)
}
makePricePrediction(c(1, 2, 3, 4, 123, 45, 55))

catimela(m1 <- lm(formula = price~area+rooms*rooms, data = offers3))
summary(m1)

m1$coefficients
# price = 4720 * area - 6116.647 * rooms - 32161

makePricePrediction <- function (area, rooms) {
  dt1 <- data.table(area = area, rooms = rooms)
  predict(m1, dt1)
}
makePricePrediction(155, 5)

offers <- fread("offers.csv")
plot(offers$rooms, offers$price)

rm(m1)
rm(offers3, offers4)
catimela(m1 <- lm(formula = price~area, data = offers))
summary(m1)

# Hipoteza 0 (Null hipothesis), w lm jest to Pr(>|t|)
# Stawiam tezę, że pomiędzy area oraz price NIE MA ZWIĄZKU
# Chcemy określić prawdopodobieństwo hipotezy 0.
# W naszym przypadku prawdopodobieństwo to wynosi < 2e-16
# Oznacza to, że H0 jest fałszywa
# Czyli price ZALEŻY od area

catimela(m2 <- lm(formula = price~area+rooms, data = offers))
summary(m2)

catimela(m3 <- lm(formula = area~rooms, data = offers))
summary(m3)

catimela(m4 <- lm(formula = price~rooms, data = offers))
summary(m4)

# Podsumowanie:
# 1. area zależy od rooms
# 2. rooms jest dla naszego modelowania bezużyteczne
# 3. price jest zależne od area!!!

catimela(m5 <- lm(formula = price~area+oneMeterPrice,
                  data = offers))
summary(m5)

catimela(m6 <- lm(formula = oneMeterPrice~area,
                  data = offers))
summary(m6)
