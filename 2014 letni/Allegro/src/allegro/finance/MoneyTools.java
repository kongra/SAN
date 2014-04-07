package allegro.finance;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import allegro.util.Coll;
import allegro.util.DynVar;

@Stateless
public class MoneyTools {

  public static MoneyTools get() {
    MoneyTools mt = VAR.value();
    if (mt == null) {
      throw new IllegalStateException("MoneyTools is not bound.");
    }
    return mt;
  }

  public static void binding(MoneyTools moneyTools, Runnable body) {
    VAR.binding(moneyTools, body);
  }

  private static final DynVar<MoneyTools> VAR = DynVar.initially(null);

  @PersistenceContext(unitName = "Allegro")
  private EntityManager em;

  @SuppressWarnings("unchecked")
  public Currency getCurrency(String code) {
    Query query = em.createNamedQuery("findCurrencyByCode");
    query.setParameter("code", code);
    return Coll.first(query.getResultList(), null);
  }

  public Currency assertCurrency(String code) {
    Currency c = getCurrency(code);
    if (c == null) {
      throw new IllegalArgumentException("Currency not found: " + code);
    }
    return c;
  }

  public Currency internCurrency(String code, String name) {
    Currency c = getCurrency(code);
    if (c != null) {
      if (!c.getName().equals(name)) {
        throw new IllegalArgumentException("Illegal requested currency name "
            + name + " for an existing currency " + c);
      }
      return c;
    }

    c = new Currency(code, name);
    em.persist(c);
    return c;
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

  @SuppressWarnings("unchecked")
  public CurrencyRates getCurrentCurrencyRates() {
    Query query = em.createNamedQuery("currencyRates");
    return Coll.first(query.getResultList(), null);
  }

  @EJB
  private CurrencyRatesHolder holder;

  public void test() {
    CurrencyRatesHolder.binding(holder, new Runnable() {
      @Override
      public void run() {
        MoneyTools.binding(MoneyTools.this, new Runnable() {
          @Override
          public void run() {
            
            Money salary = Money.of(1250, "USD");
          
          
          }
        });
      }
    });
  }
}
