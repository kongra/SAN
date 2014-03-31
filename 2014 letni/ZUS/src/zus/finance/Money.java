package zus.finance;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Money {

  public static Money of(BigDecimal amount, Currency currency) {
    return new Money(amount, currency);
  }

  public static Money of(double amount, Currency currency) {
    return of(BigDecimal.valueOf(amount), currency);
  }

  public static BigDecimal normalizedAmount(Money money, RateMemoizer rm,
      boolean trimZeros) {
    BigDecimal rate = rm.rate(money.currency);
    BigDecimal result = money.amount.multiply(rate).setScale(SCALE);
    return trimZeros ? result.stripTrailingZeros() : result;
  }

  public static BigDecimal normalizedAmount(Money money, boolean trimZeros) {
    return normalizedAmount(money, RateMemoizer.get(), trimZeros);
  }

  public static BigDecimal normalizedAmount(Money money) {
    return normalizedAmount(money, RateMemoizer.get(), false);
  }

  public static final int SCALE = 6;

  public static final RoundingMode ROUNDING_MODE = RoundingMode.UP;

  public final BigDecimal amount;

  public final Currency currency;

  private Money(BigDecimal amount, Currency currency) {
    this.amount = amount.setScale(SCALE).stripTrailingZeros();
    this.currency = currency;
  }

  @Override
  public String toString() {
    return amount.toString() + currency.toString();
  }

  @Override
  public int hashCode() {
    final boolean trimZeros = true;
    return normalizedAmount(this, trimZeros).hashCode();
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
    return normalizedAmount(this).compareTo(normalizedAmount(other)) == 0;
  }

}
