package san.bdia.app;

public record Money(long amount, Currency currency) {

  @org.jetbrains.annotations.NotNull
  @org.jetbrains.annotations.Contract("_ -> new")
  public Money in(Currency currency) {
    return new Money(
        (long) (amount / currency.usdRatio * Currency.USD.usdRatio),
        currency);
  }

  public long getAmount() {
    return amount;
  }

  public Currency getCurrency() {
    return currency;
  }
}
