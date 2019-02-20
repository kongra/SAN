library(data.table)
library(stringr)

web <- "
Nazwa waluty 	Kod waluty 	Kurs średni
bat (Tajlandia) 	1 THB 	0,1144
dolar amerykański 	1 USD 	3,7599
dolar australijski 	1 AUD 	2,7405
dolar Hongkongu 	1 HKD 	0,4804
dolar kanadyjski 	1 CAD 	2,8725
dolar nowozelandzki 	1 NZD 	2,5492
dolar singapurski 	1 SGD 	2,7417
euro 	1 EUR 	4,2951
forint (Węgry) 	100 HUF 	1,3358
frank szwajcarski 	1 CHF 	3,7504
funt szterling 	1 GBP 	4,9318
hrywna (Ukraina) 	1 UAH 	0,1345
jen (Japonia) 	100 JPY 	3,3075
korona czeska 	1 CZK 	0,1661
korona duńska 	1 DKK 	0,5757
korona islandzka 	100 ISK 	3,1101
korona norweska 	1 NOK 	0,4513
korona szwedzka 	1 SEK 	0,4186
kuna (Chorwacja) 	1 HRK 	0,5777
lej rumuński 	1 RON 	0,9210
lew (Bułgaria) 	1 BGN 	2,1960
lira turecka 	1 TRY 	0,6925
nowy izraelski szekel 	1 ILS 	1,0239
peso chilijskie 	100 CLP 	0,5567
peso meksykańskie 	1 MXN 	0,1888
piso filipińskie 	1 PHP 	0,0713
rand (Republika Południowej Afryki) 	1 ZAR 	0,2687
real (Brazylia) 	1 BRL 	1,0075
ringgit (Malezja) 	1 MYR 	0,9028
rubel rosyjski 	1 RUB 	0,0567
rupia indonezyjska 	10000 IDR 	2,5862
rupia indyjska 	100 INR 	5,1989
won południowokoreański 	100 KRW 	0,3370
yuan renminbi (Chiny) 	1 CNY 	0,5425
SDR (MFW) 	1 XDR 	5,2059
"

dt <- fread(text = web)
View(dt)

setnames(dt, "Nazwa waluty", "Currency Name")
setnames(dt, "Kod waluty",   "Currency Code")
setnames(dt, "Kurs średni",  "Value")

str(dt)

dt[`Currency Name` %like% "amer" |
   `Currency Code` == "1 AUD" ]

vs  <- dt[, Value]
vs1 <- str_replace(vs, ",", ".")
vs2 <- as.double(vs1)

dt[, Value := vs2]
View(dt)

mean(dt[, Value])
median(dt[, Value])

toPLN <- function(xs, srcCurr) {
  if (srcCurr == "PLN")
    xs
  else
    xs * dt[`Currency Code` %like% srcCurr, Value]
}

fromPLN <- function(xs, dstCurr) {
  if (dstCurr == "PLN")
    xs
  else
    xs / dt[`Currency Code` %like% dstCurr, Value]
}

recompute <- function(xs, srcCurr, dstCurr) {
  xs <- toPLN  (xs, srcCurr)
  fromPLN(xs, dstCurr)
}

microbenchmark::microbenchmark(
  recompute(c(24, 25, 25), "USD", "NOK")
)

