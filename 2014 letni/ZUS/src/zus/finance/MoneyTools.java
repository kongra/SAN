package zus.finance;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import zus.finance.nbp.NBPParser;
import zus.util.Coll;

@Stateless
public class MoneyTools {

  @PersistenceContext(unitName = "ZUS")
  private EntityManager em;
  
  @Resource
  private SessionContext ctx;

  public Currency internCurrency(String code, String name, String symbol) {
    if (StringUtils.isBlank(code)) {
      throw new IllegalArgumentException(
          "Code of the Currency must NOT be null.");
    }

    Query query = em.createNamedQuery("findCurrencyByCode");
    query.setParameter("code", code);

    @SuppressWarnings("unchecked")
    Currency curr = Coll.first(query.getResultList(), null);
    if (curr != null) {
      return curr;
    }

    curr = new Currency();
    curr.setCode(code);
    curr.setSymbol(symbol);
    curr.setName(name);

    em.persist(curr);
    return curr;
  }

  public Currency internCurrency(String code, String name) {
    return internCurrency(code, name, null);
  }

  public Currency internCurrency(String code) {
    return internCurrency(code, null, null);
  }

  public Rate internRate(BigDecimal value) {
    if (value == null) {
      throw new IllegalArgumentException("Value of the Rate must NOT be null.");
    }

    Query query = em.createNamedQuery("findRateByValue");
    query.setParameter("value", value);

    @SuppressWarnings("unchecked")
    Rate rate = Coll.first(query.getResultList(), null);
    if (rate != null) {
      return rate;
    }

    rate = new Rate(value);
    em.persist(rate);
    return rate;
  }

  public boolean importNBP() {
    return new NBPParser(em, ctx).parse();
  }
}
