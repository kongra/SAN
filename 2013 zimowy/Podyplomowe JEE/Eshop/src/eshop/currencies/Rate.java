package eshop.currencies;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY_RATE")
@NamedQuery(name = "findRateByValue", query = "Select r from Rate r where r.value = :value")
public class Rate {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY_RATE_SEQ")
  @SequenceGenerator(name = "CURRENCY_RATE_SEQ", allocationSize = 100, sequenceName = "CURRENCY_RATE_SEQ")
  private long id;

  @Column(precision = Money.PRECISION, scale = Money.SCALE, nullable = false, unique = true)
  private BigDecimal value;

  public Rate() {
  }

  public Rate(BigDecimal value) {
    this.value = value;
  }

  public BigDecimal value() {
    return value;
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
    if (!(obj instanceof Rate)) {
      return false;
    }
    Rate other = (Rate) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
