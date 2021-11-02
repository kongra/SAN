package edu.san.money;

import java.math.BigDecimal;

public final class Money {

  private final BigDecimal amount;

  private final Currency currency;

  private Money(BigDecimal amount, Currency currency) {
    this.amount = amount;
    this.currency = currency;
  }

  private Money(String amount, Currency currency) {
    this(new BigDecimal(amount), currency);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((amount == null) ? 0 : amount.hashCode());
    result = prime * result + ((currency == null) ? 0 : currency.hashCode());
    return result;
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
    if (amount == null) {
      if (other.amount != null)
        return false;
    } else if (!amount.equals(other.amount))
      return false;
    if (currency != other.currency)
      return false;
    return true;
  }

}
