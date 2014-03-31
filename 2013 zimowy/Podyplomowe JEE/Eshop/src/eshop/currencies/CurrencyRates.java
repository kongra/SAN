package eshop.currencies;

import java.sql.Timestamp;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY_RATES")
public class CurrencyRates {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY_RATES_SEQ")
  @SequenceGenerator(name = "CURRENCY_RATES_SEQ", allocationSize = 100, sequenceName = "CURRENCY_RATES_SEQ")
  private long id;

  @OneToMany
  private Map<Currency, Rate> rates;

  private Timestamp startDate;

  private Timestamp endDate;

  public Map<Currency, Rate> getRates() {
    return rates;
  }

  public void setRates(Map<Currency, Rate> rates) {
    this.rates = rates;
  }

  public Timestamp getStartDate() {
    return startDate;
  }

  public void setStartDate(Timestamp startDate) {
    this.startDate = startDate;
  }

  public Timestamp getEndDate() {
    return endDate;
  }

  public void setEndDate(Timestamp endDate) {
    this.endDate = endDate;
  }

  public long getId() {
    return id;
  }

}
