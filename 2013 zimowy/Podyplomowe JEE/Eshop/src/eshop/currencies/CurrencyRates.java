package eshop.currencies;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY_RATES")
@NamedQuery(name = "findMostRecentCurrencyRates", query = "Select cr from CurrencyRates cr order by cr.id desc")
public class CurrencyRates {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY_RATES_SEQ")
  @SequenceGenerator(name = "CURRENCY_RATES_SEQ", allocationSize = 100, sequenceName = "CURRENCY_RATES_SEQ")
  private long id;

  @ManyToMany
  private Map<Currency, Rate> rates;

  @Column(nullable = false)
  private String tableNumber;

  @Column(nullable = false)
  private Date publicationDate;

  public CurrencyRates() {
  }

  public CurrencyRates(String tableNumber, Date publicationDate,
      Map<Currency, Rate> rates) {
    this.tableNumber = tableNumber;
    this.publicationDate = publicationDate;
    this.rates = rates;
  }

  @Override
  public final int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof CurrencyRates)) {
      return false;
    }
    CurrencyRates other = (CurrencyRates) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  public Map<Currency, Rate> getRates() {
    return rates;
  }

  public String getTableNumber() {
    return tableNumber;
  }

  public Date getPublicationDate() {
    return publicationDate;
  }

}
