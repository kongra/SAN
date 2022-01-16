setwd("~/Pobrane/wiki")

library(rvest)
require(httr)
library(xml2)
library(purrr)
library(data.table)
library(stringr)

carPage <- read_html("https://en.wikipedia.org/wiki/Car")
carPage %>% html_element(css = "#firstHeading") %>% html_text2()

carPage %>% html_element(xpath = "//*[@id=\"firstHeading\"]") %>% html_text2()

carPage %>% html_element(xpath = "/html/body/div[3]/div[3]/div[5]/div[1]/div[2]/a[2]")

mwDisambigs <- carPage %>% html_elements(css = "a.mw-disambig")

href <- function (a) xml_attr(a, "href")
mwDisambigs %>% map_chr(href)

carPage %>%
  html_elements(css = "a.mw-disambig") %>%
  map_chr(href)

disambigHrefs <- function(page) {
  page %>%
    html_elements(css = "a.mw-disambig") %>%
    map_chr(href)
}

read_html("https://en.wikipedia.org/wiki/Cocoa_butter") %>% disambigHrefs()

# Zadanie. Zbuduj reprezentację stron Wikipedii nacelowaną na zagadnienie
#          analizy odnośników
addPage <- function(pagesDT, url, title, specialPageType = NA_character_) {
  recordDT <- data.table(URL = url, Title = title,
                        `Special Page Type` = specialPageType)
  rbindlist(list(pagesDT, recordDT))
}

addLink <- function(linksDT, url, linkURL, type) {
  recordDT <- data.table(URL = url, `Link URL` = linkURL, Type = type)
  rbindlist(list(linksDT, recordDT))
}

pagesDT <- data.table(URL = character(),
                      Title = character(),
                      `Special Page Type` = character())

linksDT <- data.table(URL = character(),
                      `Link URL` = character(),
                      Type = character())

analyzePage <- function(url) {
  page  <- read_html(url)
  title <- page %>%
    html_element(css = "#firstHeading") %>%
    html_text2() %>%
    str_trim()

  isDisambig <- title %>% str_ends("\\(disambiguation\\)")
  isCategory <- title %>% str_starts("Category:")
  # ... TODO: add subsequent elements
  specialPageType <- NA_character_
  if (isDisambig)
    specialPageType <- "Disambiguation"
  else if (isCategory)
    specialPageType <- "Category"

  pagesDT <<- addPage(pagesDT, url, title, specialPageType)

  # Analyze links (hrefs) in page content
  links <- page %>%
    html_element("#bodyContent") %>%
    html_elements("a") %>%
    map_chr(~ xml_attr(.x, "href"))

  # keep(links, ~ str_starts(.x, "wiki"))

  links <- links[str_starts(links, "/wiki/")]
  links <- links[!(str_starts(links, "/wiki/File:"))]
  links <- links[!is.na(links)]

  for (i in seq_along(links)) {
    linksDT <<- addLink(linksDT, url, links[i], NA_character_)
  }
}

analyzePage("https://en.wikipedia.org/wiki/SOAP")
analyzePage("https://en.wikipedia.org/wiki/Car_(disambiguation)")

# | Page URL                          | URL                                                | Type           |
# |-----------------------------------+----------------------------------------------------+----------------|
# | https://en.wikipedia.org/wiki/Car | https://en.wikipedia.org/wiki/Car_(disambiguation) | Disambiguation |
# | https://en.wikipedia.org/wiki/Car | https://en.wikipedia.org/wiki/CARS                 | Disambiguation |
# |                                   |                                                    |                |

read_html("https://www.otomoto.pl/osobowe") %>%
  html_elements(xpath = "/html/body/div[1]/div/div/div/div[2]/div[2]/div[2]/div[1]/div[3]/main/article[28]/div[1]/h2")
