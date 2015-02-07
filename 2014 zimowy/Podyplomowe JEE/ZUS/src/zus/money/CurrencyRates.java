package zus.money;

import java.math.BigDecimal;

import zus.util.DynVar;

import com.google.common.collect.ImmutableMap;

public final class CurrencyRates {

  public static BigDecimal normalizedValue(Currency c, BigDecimal amount) {
    CurrencyRates rates = dynvar.value();
    BigDecimal rate = rates.rates.get(c);
    return amount.multiply(rate, Money.CTX);
  }

  public static void withRates(CurrencyRates rates, Runnable body) {
    dynvar.binding(rates, body);
  }

  private static DynVar<CurrencyRates> dynvar = DynVar.initially(null);

  private final ImmutableMap<Currency, BigDecimal> rates;

  private CurrencyRates(ImmutableMap<Currency, BigDecimal> rates) {
    super();
    this.rates = rates;
  }

}
