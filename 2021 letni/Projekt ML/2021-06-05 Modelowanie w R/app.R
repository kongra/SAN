library(data.table)
library(ggplot2)
library(shiny)
library(chR)

# DATA PART
dt <- fread("offers.csv")

m1 <- lm(formula = price~area, data = dt)

MIN_AREA <-  10
MAX_AREA <- 200
areaInRange <- function (area) {
  area >= MIN_AREA && area <= MAX_AREA
}

chArea <- chAnd(chNumeric, chP(function(x) !is.nan(x)), chP(areaInRange))

suggest <- function(area) chDouble({
  chArea(area)
  predict(m1, data.table(area = area))[[1]]
})

generatePlot <- function(area) chGgplot({
  chNumeric(area)
  a1 <- aes(x = area, y = price)
  s  <- suggest(area)
  ds <- format(round(s, 2), nsmall = 2) # 2 decimal places for disp. suggestion
  ggplot(data = dt) + geom_point(a1, shape = 19, alpha = 0.25) +
    geom_smooth(a1, method = "lm") +
    geom_vline(xintercept = area, linetype = "dotted") +
    geom_hline(yintercept = s, linetype = "dotted") +
    ggtitle(paste("Suitable price is", ds, "PLN"))
})

# WEB PART
ui <- fluidPage(
  sliderInput(inputId = "area",
              label   = "Area [square meters]",
              min     = MIN_AREA,
              max     = MAX_AREA,
              value   = 55),

  plotOutput(outputId = "resultPlot"))

server <- function(input, output) {
  output$resultPlot <- renderPlot({ generatePlot(input$area) })
}

shinyApp(ui = ui, server = server)
