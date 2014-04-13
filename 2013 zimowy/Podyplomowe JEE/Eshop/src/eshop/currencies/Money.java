package eshop.currencies;

import java.math.BigDecimal;

public final class Money {
  
  public static final int PRECISION = 20;
  
  public static final int SCALE = 5;

  public static Money of(double amount, Currency currency) {
    return of(BigDecimal.valueOf(amount), currency);
  }

  public static Money of(BigDecimal amount, Currency currency) {
    return new Money(amount, currency);
  }

  public static Money of(double amount, String currencyCode) {
    return of(amount, MoneyTools.ensure().ensureCurrency(currencyCode));
  }

  public static Money of(BigDecimal amount, String currencyCode) {
    return new Money(amount, MoneyTools.ensure().ensureCurrency(currencyCode));
  }

  public final BigDecimal amount;

  public final Currency currency;

  private Money(BigDecimal amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public Money add(Money other) {
    BigDecimal otherAmount =
        MoneyTools.ensure().normalize(other.amount, other.currency);
    return new Money(this.amount.add(otherAmount), this.currency);
  }

  @Override
  public int hashCode() {
    return MoneyTools.ensure().normalize(this.amount, this.currency)
        .stripTrailingZeros().hashCode();
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

    MoneyTools moneyTools = MoneyTools.ensure();
    BigDecimal thisAmount = moneyTools.normalize(this.amount, this.currency);
    BigDecimal otherAmount = moneyTools.normalize(other.amount, other.currency);

    return thisAmount.compareTo(otherAmount) == 0;
  }

  @Override
  public String toString() {
    return amount.toString() + currency.toString();
  }

}
