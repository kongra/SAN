package ewus;

import java.math.BigDecimal;

public final class Money {

  public final BigDecimal value;

  public final Currency currency;

  public Money(BigDecimal value, Currency currency) {
    this.value = value;
    this.currency = currency;
  }

  @Override
  public int hashCode() {
    return Currency.dollarValue(this.value, this.currency).stripTrailingZeros()
        .hashCode();
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
    BigDecimal thisDollarValue =
        Currency.dollarValue(this.value, this.currency);
    BigDecimal otherDollarValue =
        Currency.dollarValue(other.value, other.currency);

    return thisDollarValue.compareTo(otherDollarValue) == 0;
  }

  @Override
  public String toString() {
    return value.toString() + currency.name();
  }

}
