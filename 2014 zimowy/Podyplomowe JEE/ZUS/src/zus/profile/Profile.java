package zus.profile;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import zus.money.Currency;
import zus.money.Money;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "profile_type")
@Access(AccessType.FIELD)
@Table(name = "profiles")
@NamedQueries({
  @NamedQuery(name = "findProfileByLogin", query = "Select p from Profile p where p.login = :login"),
  @NamedQuery(name = "findProfileIdByLogin", query = "Select p.id from Profile p where p.login = :login"),
})
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_seq")
  @SequenceGenerator(name = "profile_seq", sequenceName = "profile_seq", allocationSize = 100)
  private long id;
  
  @Version
  private long version;

  @Column(nullable = false, unique = true)
  private String login;

  @Column(nullable = false)
  private String passwd;

  private String firstName;

  private String lastName;

  @Transient
  private Money contrib;

  @Access(AccessType.PROPERTY)
  private BigDecimal getContribAmount() {
    return null == contrib ? null : contrib.amount;
  }

  public void setContribAmount(BigDecimal amount) {
    Currency c = null == contrib ? Currency.PLN : contrib.currency;
    contrib = new Money(c, amount);
  }

  @Access(AccessType.PROPERTY)
  @Enumerated(EnumType.ORDINAL)
  private Currency getContribCurrency() {
    return null == contrib ? null : contrib.currency;
  }

  public void setContribCurrency(Currency c) {
    BigDecimal a = null == contrib ? BigDecimal.ZERO : contrib.amount;
    contrib = new Money(c, a);
  }

  public Money getContrib() {
    return contrib;
  }

  public void setContrib(Money contrib) {
    this.contrib = contrib;
  }

  public long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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
    if (!(obj instanceof Profile)) {
      return false;
    }
    Profile other = (Profile) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
