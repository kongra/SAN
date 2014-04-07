package allegro.finance;

import java.math.BigDecimal;

public final class Money {

  public static Money of(BigDecimal amount, Currency currency) {
    return new Money(amount, currency);
  }

  public static Money of(double amount, Currency currency) {
    return of(BigDecimal.valueOf(amount), currency);
  }

  public static Money of(String amount, Currency currency) {
    return of(new BigDecimal(amount), currency);
  }

  public static Money of(BigDecimal amount, String currencyCode) {
    return of(amount, MoneyTools.get().assertCurrency(currencyCode));
  }

  public static Money of(double amount, String currencyCode) {
    return of(amount, MoneyTools.get().assertCurrency(currencyCode));
  }

  public static Money of(String amount, String currencyCode) {
    return of(amount, MoneyTools.get().assertCurrency(currencyCode));
  }

  private final BigDecimal amount;

  private final Currency currency;

  private Money(BigDecimal amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  @Override
  public String toString() {
    return amount.toString() + currency.toString();
  }

  @Override
  public int hashCode() {
    CurrencyRatesHolder rh = CurrencyRatesHolder.get();
    BigDecimal amount = rh.nomalize(this.amount, this.currency);
    return amount.stripTrailingZeros().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Money other = (Money) obj;

    CurrencyRatesHolder rh = CurrencyRatesHolder.get();
    BigDecimal thisAmount = rh.nomalize(this.amount, this.currency);
    BigDecimal otherAmount = rh.nomalize(other.amount, other.currency);

    return thisAmount.compareTo(otherAmount) == 0;
  }

}
