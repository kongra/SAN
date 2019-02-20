euler1 <- function() {
  n <- 0L
  result <- 0L
  while (n < 1000L) {
    if (n %% 3L == 0L || n %% 5L == 0L) {
      result <- result + n
    }
    n <- n + 1L
  }
  result
}

# install.packages("microbenchmark")
library(microbenchmark)

euler11 <- function() {
  ns <- 1:999
  sum(ns[ns %% 3L == 0L | ns %% 5L == 0L])
}

euler1 <- compiler::cmpfun(euler1)

# install.packages("Rcpp")
Rcpp::cppFunction("
  uint32_t euler12() {
    uint32_t result = 0;
    for (uint32_t i = 0; i < 1000; i++)
      if (i % 3 == 0 || i % 5 == 0)
        result += i;

    return result;
  }
")

euler12()
microbenchmark(euler1(), euler11(), euler12())

x <- 3.14
typeof(x)
pryr::bits(x)
pryr::object_size(x)

y <- c(3.14, 0.25, 12, 75.2)
typeof(y)
pryr::object_size(y)
pryr::bits(y)

n <- c(1, 2, 3, 4)
typeof(n)
typeof(1L)

n <- c(1L, 2L, 3L, 4L)
typeof(n)
pryr::object_size(n)
pryr::bits(n)

s <- c("This", "is", "our", "R", "course")
typeof(s)

b1 <- c(TRUE, FALSE, TRUE)
typeof(b1)
pryr::bits(b1)

nucleotides <- c("A", "C", "T", "G")
dna <- sample(nucleotides, 1e6, replace = TRUE)
typeof(dna)
pryr::object_size(dna)

f1 <- as.factor(dna)
typeof(f1)
pryr::object_size(f1)

b1[0L]

m1 <- list()
m1[["x"]] <- 1L
m1[["y"]] <- 2.34

m2 <- list()
m2[[1L]] <- "abcd"
m2[[2L]] <- c(3.14, 2.71)
