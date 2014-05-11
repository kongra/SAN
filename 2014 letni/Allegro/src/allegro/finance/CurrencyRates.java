package allegro.finance;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY_RATES")
@NamedQueries({
  @NamedQuery(name = "currencyRates", query = "Select cr from CurrencyRates cr order by cr.importTime desc"),
  @NamedQuery(name = "findCurrencRatesByTableNumber", query = "Select cr from CurrencyRates cr where cr.tableNumber = :tableNumber")
})
public class CurrencyRates {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURRENCY-RATES-ID-GEN")
  @SequenceGenerator(name = "CURRENCY-RATES-ID-GEN", sequenceName = "CURRENCY_RATES_ID_SEQ", allocationSize = 100)
  private long id;

  @Column(nullable = false, unique = true)
  private Timestamp importTime;

  @Column(nullable = false)
  private Date publicationDate;

  @Column(nullable = false, unique = true)
  private String tableNumber;

  @ManyToMany
  private Map<Currency, Rate> rates;

  public CurrencyRates() {

  }

  public CurrencyRates(Date publicationDate, String tableNumber,
      Map<Currency, Rate> rates) {
    this.publicationDate = publicationDate;
    this.tableNumber = tableNumber;
    this.rates = rates;
    this.importTime = new Timestamp(System.currentTimeMillis());
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

  public long getId() {
    return id;
  }

  public Timestamp getImportTime() {
    return importTime;
  }

  public Map<Currency, Rate> getRates() {
    return rates;
  }

}
