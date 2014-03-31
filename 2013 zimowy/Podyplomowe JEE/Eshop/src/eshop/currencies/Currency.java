package eshop.currencies;

import java.math.BigDecimal;

public enum Currency {

  PLN("zł", 0.329696), GBP("£", 1.6645), USD("$", 1);

  public final String symbol;

  public final BigDecimal exchangeRate;
  
  private Currency(String symbol, double exchangeRate) {
    this.symbol = symbol;
    this.exchangeRate = BigDecimal.valueOf(exchangeRate);
  }
  
}
