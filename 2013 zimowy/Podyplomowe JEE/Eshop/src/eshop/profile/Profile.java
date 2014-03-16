package eshop.profile;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Inheritance
@DiscriminatorColumn(name = "PROFILE_TYPE")
@Table(name = "PROFILES")
@NamedQueries({
    @NamedQuery(name = "findProfileByLogin", query = "Select p from Profile p where p.login = :login"),
    @NamedQuery(name = "findProfileIDByLogin", query = "Select p.id from Profile p where p.login = :login")
})
public abstract class Profile {

  public static final String TAG = "profile";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILES_SEQ")
  @SequenceGenerator(name = "PROFILES_SEQ", allocationSize = 100, sequenceName = "PROFILES_SEQ")
  private long id;

  @Version
  private long version;
  
  @Column(unique = true)
  private String login;

  private String password;

  @ManyToOne
  private Address address;

  public long id() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public final int hashCode() {
    long id = id();
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
    if (id() != other.id()) {
      return false;
    }
    return true;
  }

}
