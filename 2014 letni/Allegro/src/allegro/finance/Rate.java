package allegro.finance;

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
@Table(name = "RATES")
@NamedQuery(name = "findRateByValue", query = "Select r from Rate r where r.value = :value")
public class Rate {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATES-ID-GEN")
  @SequenceGenerator(name = "RATES-ID-GEN", sequenceName = "RATES_ID_SEQ", allocationSize = 100)
  private long id;

  @Column(scale = 6, precision = 20, nullable = false, unique = true)
  private BigDecimal value;

  public Rate() {

  }

  public Rate(BigDecimal value) {
    this.value = value;
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

  public long getId() {
    return id;
  }

  public BigDecimal getValue() {
    return value;
  }

}
