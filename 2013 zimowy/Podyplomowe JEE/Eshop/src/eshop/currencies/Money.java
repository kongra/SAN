package eshop.currencies;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Money {

  public static Money of(double amount, Currency currency) {
    return of(BigDecimal.valueOf(amount), currency);
  }

  public static Money of(BigDecimal amount, Currency currency) {
    return new Money(amount, currency);
  }

  public static Money toUSD(Money money) {
    if (money.currency == Currency.USD) {
      return money;
    }
    return new Money(toUSDAmount(money.amount, money.currency), Currency.USD);
  }

  public static BigDecimal convertAmount(BigDecimal amount,
      Currency srcCurrency, Currency dstCurrency) {
    BigDecimal usdAmount = toUSDAmount(amount, srcCurrency);
    return fromUSDAmount(usdAmount, dstCurrency);
  }

  public static BigDecimal toUSDAmount(BigDecimal amount, Currency currency) {
    if (currency == Currency.USD) {
      return amount;
    }
    
    System.out.println("amount " + amount);
    System.out.println("currency " + currency);
    
    return amount.multiply(currency.exchangeRate);
  }

  public static BigDecimal fromUSDAmount(BigDecimal amount, Currency currency) {
    if (currency == Currency.USD) {
      return amount;
    }
    return amount.divide(currency.exchangeRate, 6, RoundingMode.HALF_UP);
  }

  public final BigDecimal amount;

  public final Currency currency;

  private Money(BigDecimal amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public Money add(Money other) {
    BigDecimal otherAmount =
        convertAmount(other.amount, other.currency, this.currency);
    return new Money(this.amount.add(otherAmount), this.currency);
  }

  @Override
  public int hashCode() {
    return toUSDAmount(this.amount, this.currency).stripTrailingZeros()
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
    BigDecimal amount = toUSDAmount(this.amount, this.currency);
    BigDecimal otherAmount = toUSDAmount(other.amount, other.currency);

    return amount.compareTo(otherAmount) == 0;
  }

  @Override
  public String toString() {
    return amount.toString() + currency.symbol;
  }

}
