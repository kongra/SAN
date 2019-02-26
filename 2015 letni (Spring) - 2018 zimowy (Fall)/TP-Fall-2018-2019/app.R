library(data.table)
library(keras)
library(shiny)
library(png)

network <- load_model_hdf5("net.hdf5")

ui <- fluidPage(
  titlePanel("MNIST in action"),
  fileInput(inputId = "inFile",
            label   = "Select PNG",
            accept  = c(".png", ".PNG")),

  h1(uiOutput(outputId = "uiDigit"))
)

server <- function(input, output) {
  output[["uiDigit"]] <- renderUI({
    inFile <- input[["inFile"]]

    if (!is.null(inFile)) {
      png <- inFile[["datapath"]] %>%
        readPNG %>%
        EBImage::resize(w = 28, h = 28)

      png <- array_reshape(1.0 - png, c(1, 28 * 28))
      cls <- network %>% predict_classes(png)
      paste0("Your digit is ", cls)
    }

  })
}

shinyApp(ui = ui, server = server)
