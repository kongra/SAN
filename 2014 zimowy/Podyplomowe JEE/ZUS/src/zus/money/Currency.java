package zus.money;

public enum Currency {
  THB ("bat (Tajlandia)"),
  USD ("dolar amerykański"),
  AUD ("dolar australijski"),
  HKD ("dolar Hongkongu"),
  CAD ("dolar kanadyjski"),
  NZD ("dolar nowozelandzki"),
  SGD ("dolar singapurski"),
  EUR ("euro"),
  HUF ("forint (Węgry)"),
  CHF ("frank szwajcarski"),
  GBP ("funt szterling"),
  UAH ("hrywna (Ukraina)"),
  JPY ("jen (Japonia)"),
  CZK ("korona czeska"),
  DKK ("korona duńska"),
  ISK ("korona islandzka"),
  NOK ("korona norweska"),
  SEK ("korona szwedzka"),
  HRK ("kuna chorwacka"),
  RON ("lej rumuński"),
  BGN ("lew bułgarski"),
  TRY ("lira turecka"),
  ILS ("nowy izraelski szekel"),
  CLP ("peso chilijskie"),
  PHP ("peso filipińskie"),
  MXN ("peso meksykańskie"),
  ZAR ("rand (Republika Południowej Afryki)"),
  BRL ("real brazylijski"),
  MYR ("ringgit malezyjski"),
  RUB ("rubel rosyjski"),
  IDR ("rupia indonezyjska"),
  INR ("rupia indyjska"),
  KRW ("won (Korea Południowa)"),
  CNY ("yuan renminbi (Chiny)"),
  XDR ("SDR (MFW)"),
  PLN ("złoty polski");

  private String fullName;

  private Currency(String fullName) {
    this.fullName = fullName;
  }
}
