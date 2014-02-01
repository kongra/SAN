package ewus;

import java.math.BigDecimal;

public enum Currency {

  PLN(dollarRate(0.317329)), GBP(dollarRate(1.6432)), USD(dollarRate(1.0));

  private final BigDecimal dollarRate;

  private Currency(BigDecimal exchangeRate) {
    this.dollarRate = exchangeRate;
  }

  public static BigDecimal dollarRate(double rate) {
    return BigDecimal.valueOf(rate);
  }

  public static BigDecimal dollarValue(BigDecimal value, Currency currency) {
    return value.multiply(currency.dollarRate);
  }
}
