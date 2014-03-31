package zus.finance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCIES")
@NamedQuery(name = "findCurrencyByCode", query = "Select c from Currency c where c.code = :code")
public class Currency {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCIES-ID-GEN")
  @SequenceGenerator(name = "CURRENCIES-ID-GEN", allocationSize = 100, sequenceName = "CURRENCIES_ID_SEQ")
  private long id;

  @Column(nullable = false, unique = true)
  private String code;

  private String name;

  private String symbol;

  @Override
  public String toString() {
    if (symbol != null) {
      return symbol;
    }
    return code;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
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

}
