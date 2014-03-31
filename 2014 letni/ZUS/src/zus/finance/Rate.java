package zus.finance;

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
  @SequenceGenerator(name = "RATES-ID-GEN", allocationSize = 100, sequenceName = "RATES_ID_SEQ")
  private long id;

  @Column(nullable = false, unique = true, precision = 20, scale = Money.SCALE)
  private BigDecimal value;

  public Rate() {
  }

  public long getId() {
    return id;
  }

  public Rate(BigDecimal value) {
    this.value = value;
  }

  public BigDecimal value() {
    return value;
  }

}
