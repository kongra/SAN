package zus.finance;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RATES_COLLS")
@NamedQuery(name = "findMostRecentRatesColl", query = "Select rc from RatesColl rc order by rc.id desc")
public class RatesColl {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATES-COLLS-ID-GEN")
  @SequenceGenerator(name = "RATES-COLLS-ID-GEN", allocationSize = 100, sequenceName = "RATES_COLLS_ID_SEQ")
  private long id;

  @ManyToMany
  private Map<Currency, Rate> rates;

  private Date publicationDate;

  private Timestamp importTime;

  public RatesColl(Date publicationDate, Map<Currency, Rate> rates) {
    this.rates = rates;
    this.publicationDate = publicationDate;
    this.importTime = new Timestamp(System.currentTimeMillis());
  }

  public RatesColl() {
  }

  public Map<Currency, Rate> getRates() {
    return rates;
  }

  public Date getPublicationDate() {
    return publicationDate;
  }

  public Timestamp getImportTime() {
    return importTime;
  }

  public long getId() {
    return id;
  }

}
