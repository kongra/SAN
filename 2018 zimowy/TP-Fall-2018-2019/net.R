# install.packages("keras")
# keras::install_keras()

library(keras)
library(data.table)
library(koR)

set.seed(12345)
keras::use_session_with_seed(12345)

mnist <- dataset_mnist()
str(mnist)

trainX <- mnist[["train"]][["x"]]
dim(trainX)
# [1] 60000 28 28

trainY <- mnist[["train"]][["y"]]
dim(trainY)
# [1] 60000

testX <- mnist[["test"]][["x"]]
dim(testX)
# [1] 10000 28 28

testY <- mnist[["test"]][["y"]]
dim(testY)
# [1] 10000

network <- keras_model_sequential() %>%
  layer_dense(units       = 512,
              activation  = "relu",
              input_shape = c(28 * 28)) %>%
  layer_dense(units       = 512,
              activation  = "relu") %>%
  layer_dense(units       = 256,
              activation  = "relu") %>%
  layer_dense(units       = 10,
              activation  = "softmax")

network %>% compile(
  optimizer = "rmsprop",
  loss      = "categorical_crossentropy",
  metrics   = c("accuracy"))

trainX <- array_reshape(trainX, c(60000, 28 * 28))
trainX <- trainX / 255

testX <- array_reshape(testX, c(10000, 28 * 28))
testX <- testX / 255

trainY_CAT <- to_categorical(trainY)
dim(trainY_CAT)

dim(trainY)
# View(trainY[1:10])

# dim(testY_CAT)
# View(testY_CAT[1:10, ])

catimela({
  network %>%
    fit(trainX, trainY_CAT, epochs = 10, batch_size = 128)
})

catimela({
  network %>% predict_classes(testX)
})

dt <- data.table(
  y     = testY,
  yPred = network %>% predict_classes(testX)
)

# View(dt[y != yPred])
nrow(dt[y != yPred])

1 - (nrow(dt[y != yPred]) / nrow(dt))

save_model_hdf5(network, filepath = "net.hdf5")
