package allegro.finance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "CURRENCIES")
@NamedQueries({
  @NamedQuery(name="findCurrencyByCode", query="Select c from Currency c where c.code = :code")
})
public class Currency {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY-ID-GEN")
  @SequenceGenerator(name = "CURRENCY-ID-GEN", sequenceName = "CURRENCIES_ID_SEQ", allocationSize = 100)
  private long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true, length = 3)
  private String code;

  private String symbol;

  public Currency() {
  }

  public Currency(String code, String name) {
    this.code = code;
    this.name = name;
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
    String s = getSymbol();
    if (StringUtils.isBlank(s)) {
      return getCode();
    }
    return s;
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

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

}
