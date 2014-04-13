package eshop.currencies;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "CURRENCIES")
@NamedQuery(name = "findCurrencyByCode", query = "Select c from Currency c where c.code=:code")
public class Currency {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY-GEN")
  @SequenceGenerator(name = "CURRENCY-GEN", allocationSize = 100, sequenceName = "CURRENCIES_SEQ")
  private long id;

  @Column(nullable = false, unique = true)
  private String code;

  @Column(nullable = false)
  private String name;

  private String symbol;

  public Currency() {
  }

  public Currency(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public Currency(String code, String name, String symbol) {
    this.code = code;
    this.name = name;
    this.symbol = symbol;
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
    if (!(obj instanceof Currency)) {
      return false;
    }
    Currency other = (Currency) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    String symbol = getSymbol();
    if (!StringUtils.isBlank(symbol)) {
      return symbol;
    }

    return getCode();
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public long getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

}
