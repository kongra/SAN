package eshop.currencies;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
public class CurrencyRatesBean {

  private final Map<Currency, BigDecimal> rates = new HashMap<>();

  @Lock(LockType.READ)
  public BigDecimal getRate(Currency currency) {
    return rates.get(currency);
  }

  @Lock(LockType.WRITE)
  public void setRates(Map<Currency, BigDecimal> rates) {
    this.rates.clear();
    this.rates.putAll(rates);
  }

  @PostConstruct
  private void initialize() {
    System.out.println("Wczytuję aktualne kursy walut z bazy");
    // TODO
  }
}
