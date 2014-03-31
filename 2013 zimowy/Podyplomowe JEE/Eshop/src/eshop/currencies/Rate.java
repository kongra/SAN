package eshop.currencies;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY_RATE")
public class Rate {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY_RATE_SEQ")
  @SequenceGenerator(name = "CURRENCY_RATE_SEQ", allocationSize = 100, sequenceName = "CURRENCY_RATE_SEQ")
  private long id;

  private BigDecimal value;

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public long getId() {
    return id;
  }

}
