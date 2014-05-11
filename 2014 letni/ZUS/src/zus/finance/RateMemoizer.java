package zus.finance;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import zus.util.DynVar;

@Singleton
public class RateMemoizer {

  public static final DynVar<RateMemoizer> VAR = DynVar.initially(null);

  public static RateMemoizer get() {
    RateMemoizer memoizer = VAR.value();
    if (memoizer == null) {
      throw new IllegalStateException("Can't bind the RateMemoizer.");
    }
    return memoizer;
  }

  @EJB
  private MoneyTools moneyTools;

  private final Map<Currency, BigDecimal> rates = new HashMap<>();

  @Lock(LockType.READ)
  public BigDecimal rate(Currency currency) {
    return rates.get(currency);
  }

  @Lock(LockType.WRITE)
  public void update(Map<Currency, BigDecimal> rates) {
    rates.putAll(rates);
  }
  
  @Lock(LockType.WRITE)
  public void set(Map<Currency, BigDecimal> rates) {
    rates.clear();
    rates.putAll(rates);
  }

  @PostConstruct
  private void initialize() {
    RatesColl rc = moneyTools.findMostRecentRatesColl();
    if (rc == null) {
      System.out.println("Nie ma żadnego RatesColl. "
          + "RateMemoizer pozostaje pusty");
      return;
    }

    Map<Currency, Rate> r = rc.getRates();
    Map<Currency, BigDecimal> newRates = new HashMap<>(r.size());
    for (Map.Entry<Currency, Rate> entry : r.entrySet()) {
      newRates.put(entry.getKey(), entry.getValue().value());
    }

    set(newRates);

    System.out.println("RateMemoizer zainicjowany najnowszymi kursami walut.");
  }
}
