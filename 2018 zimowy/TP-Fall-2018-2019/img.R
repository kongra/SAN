library(keras)
library(koR)

catimela({
  network <- load_model_hdf5("net.hdf5")
})

install.packages("png")
source("http://bioconductor.org/biocLite.R")
biocLite("EBImage")

library(png)

png <- readPNG("/home/kongra/Pulpit/1.png")
dim(png)
typeof(png)
class(png)

png <- EBImage::resize(png, w = 28, h = 28)
png <- 1.0 - png

png <- array_reshape(png, c(1, 28 * 28))

catimela({
  network %>% predict_classes(png)
})

microbenchmark::microbenchmark({
  network %>% predict_classes(png)
})
