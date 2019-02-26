library(data.table)
library(ggplot2)

xs <- seq(from = 0, to = 1, length.out = 1000)
ys <- exp(xs) + rnorm(1000, sd = 30)
dt <- data.table(x = xs, y = ys)

ggplot(data = dt, aes(x, y)) +
  geom_point(alpha = 0.2)

cor(dt[["x"]], dt[["y"]], method = "pearson")

m1 <- lm(y ~ x, data = dt)
predict(m1, data.table(x = 0.4))

summary(m1)
