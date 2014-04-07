package allegro.finance;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import allegro.util.DynVar;

@Singleton
public class CurrencyRatesHolder {

  public static CurrencyRatesHolder get() {
    CurrencyRatesHolder rh = VAR.value();
    if (rh == null) {
      throw new IllegalStateException("CurrencyRatesHolder is not bound.");
    }
    return rh;
  }

  public static void binding(CurrencyRatesHolder rh, Runnable body) {
    VAR.binding(rh, body);
  }

  private static final DynVar<CurrencyRatesHolder> VAR = DynVar.initially(null);

  private final Map<Currency, BigDecimal> rates = new HashMap<>();

  @EJB
  private MoneyTools moneyTools;

  @Lock(LockType.READ)
  public BigDecimal nomalize(BigDecimal amount, Currency currency) {
    BigDecimal rate = rates.get(currency);
    if (null == rate) {
      throw new IllegalArgumentException("Rate not found for " + currency);
    }
    if (BigDecimal.ONE.compareTo(rate) == 0) {
      return amount;
    }

    return amount.multiply(rate);
  }

  @PostConstruct
  private void initialize() {
    CurrencyRates currencyRates = moneyTools.getCurrentCurrencyRates();
    if (currencyRates == null) {
      System.out.println("No CurrencyRates to import into CurrencyRatesHolder.");
      return;
    }

    Map<Currency, Rate> rts = currencyRates.getRates();
    Map<Currency, BigDecimal> newRates = new HashMap<>(rts.size());

    for (Map.Entry<Currency, Rate> entry : rts.entrySet()) {
      newRates.put(entry.getKey(), entry.getValue().getValue());
    }

    set(newRates);
    System.out.println("CurrencyRates imported into CurrencyRatesHolder.");
  }

  @Lock(LockType.WRITE)
  public void update(Map<Currency, BigDecimal> rates) {
    this.rates.putAll(rates);
  }

  @Lock(LockType.WRITE)
  public void update(Currency currency, BigDecimal rate) {
    this.rates.put(currency, rate);
  }

  @Lock(LockType.WRITE)
  public void set(Map<Currency, BigDecimal> rates) {
    this.rates.clear();
    this.rates.putAll(rates);
  }
}
