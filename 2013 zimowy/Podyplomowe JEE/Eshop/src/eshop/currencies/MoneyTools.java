package eshop.currencies;

import java.math.BigDecimal;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eshop.utils.Coll;
import eshop.utils.DynVar;

@Stateless
public class MoneyTools {

  private static final DynVar<MoneyTools> VAR = DynVar.initially(null);

  public static MoneyTools ensure() {
    MoneyTools value = VAR.value();
    if (value == null) {
      throw new IllegalStateException(
          "MoneyTools dynamic instance was not bound.");
    }

    return value;
  }

  public static void binding(MoneyTools moneyTools, Runnable body) {
    VAR.binding(moneyTools, body);
  }

  @PersistenceContext(unitName = "EShop")
  private EntityManager em;

  @EJB
  private CurrencyRatesHolder ratesHolder;

  @SuppressWarnings("unchecked")
  public Currency currencyOfCode(String code) {
    Query query = em.createNamedQuery("findCurrencyByCode");
    query.setParameter("code", code);
    return Coll.first(query.getResultList(), null);
  }

  public Currency ensureCurrency(String code) {
    Currency c = currencyOfCode(code);
    if (c == null) {
      throw new IllegalArgumentException("Currency code not recognized " + code);
    }
    return c;
  }

  public Currency internCurrency(String code, String name) {
    Currency c = currencyOfCode(code);
    if (c != null) {
      if (name.equals(c.getName())) {
        return c;
      }
      throw new IllegalArgumentException("Currency names mismatch: "
          + c.getName() + " and " + name);
    }

    c = new Currency(code, name);
    em.persist(c);
    return c;
  }

  public BigDecimal normalize(BigDecimal amount, Currency currency) {
    BigDecimal rate = ratesHolder.getRate(currency);
    if (rate == null) {
      throw new IllegalArgumentException("Unknown currency " + currency);
    }

    if (rate.compareTo(BigDecimal.ONE) == 0) {
      return amount;
    }
    return amount.multiply(rate);
  }

  public Rate internRate(BigDecimal value) {
    Query query = em.createNamedQuery("findRateByValue");
    query.setParameter("value", value);

    @SuppressWarnings("unchecked")
    Rate r = Coll.first(query.getResultList(), null);
    if (r != null) {
      return r;
    }

    r = new Rate(value);
    em.persist(r);
    return r;
  }

  public Map<Currency, Rate> currentCurrencyRates() {
    Query query = em.createNamedQuery("findMostRecentCurrencyRates");
    query.setMaxResults(1);

    @SuppressWarnings("unchecked")
    CurrencyRates cr = Coll.first(query.getResultList(), null);
    if (cr == null) {
      return null;
    }

    return cr.getRates();
  }

}
