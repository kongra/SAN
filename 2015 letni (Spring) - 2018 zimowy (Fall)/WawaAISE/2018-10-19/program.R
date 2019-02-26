euler1 <- function() {
  i <- 0
  result <- 0
  while (i < 1000) {
    if (i %% 3 == 0 || i %% 5 == 0) {
      # cat("i is", i, "\n")
      result <- result + i
    }
    i <- i + 1
  }
  result
}

euler1()

# install.packages("microbenchmark")
library(microbenchmark)

euler1a <- function() {
  nums <- 0:999
  sum(nums[nums %% 3 == 0 | nums %% 5 == 0])
}

library(Rcpp)
cppFunction('
uint euler1b() {
  uint result = 0;
  for (uint i = 0; i < 1000; i++) {
    if (i % 3 == 0 || i % 5 == 0)
      result += i;
  }
  return result;
}')

microbenchmark(euler1(), euler1a(), euler1b())
