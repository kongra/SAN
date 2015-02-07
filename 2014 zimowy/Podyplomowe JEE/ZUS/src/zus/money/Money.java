package zus.money;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public final class Money {

  public static final MathContext CTX = new MathContext(12, RoundingMode.UP);

  public final Currency currency;

  public final BigDecimal amount;
  
  public Money(Currency currency, BigDecimal amount) {
    this.currency = currency;
    this.amount = amount;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    BigDecimal thisAmount =
        CurrencyRates.normalizedValue(this.currency, this.amount);
    return prime * thisAmount.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    Money other = (Money) obj;
    BigDecimal thisAmount =
        CurrencyRates.normalizedValue(this.currency, this.amount);
    BigDecimal otherAmount =
        CurrencyRates.normalizedValue(other.currency, other.amount);

    return thisAmount.equals(otherAmount);
  }

}
