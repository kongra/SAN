package zus.finance;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
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
    // 1. READ rates FROM JPA - TODO
  }
}
