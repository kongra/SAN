package eshop.currencies;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
public class CurrencyRatesHolder {

  private final Map<Currency, BigDecimal> rates = new HashMap<>();

  @EJB
  private MoneyTools moneyTools;

  @Lock(LockType.READ)
  public BigDecimal getRate(Currency currency) {
    return rates.get(currency);
  }

  @Lock(LockType.WRITE)
  public void setRates(Map<Currency, BigDecimal> rates) {
    this.rates.clear();
    this.rates.putAll(rates);
  }

  @Lock(LockType.WRITE)
  public void update(Map<Currency, BigDecimal> rates) {
    this.rates.putAll(rates);
  }

  @Lock(LockType.WRITE)
  public void update(Currency currency, BigDecimal rate) {
    this.rates.put(currency, rate);
  }

  public void updateRaw(Map<Currency, Rate> rates) {
    Map<Currency, BigDecimal> updates = new HashMap<>();
    for (Map.Entry<Currency, Rate> entry : rates.entrySet()) {
      updates.put(entry.getKey(), entry.getValue().value());
    }

    update(updates);
  }
  
  @PostConstruct
  private void initialize() {
    System.out.println("Reading current rates ...");

    Map<Currency, Rate> currates = moneyTools.currentCurrencyRates();
    if (currates == null || currates.isEmpty()) {
      System.out.println("... current rates not found.");
      return;
    }
    updateRaw(currates);
    System.out.println("... rates read.");
  }
}
