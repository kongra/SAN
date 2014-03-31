package allegro.profile;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "PROFILES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PROFILE_TYPE")
@NamedQuery(name = "findProfileByLogin", query = "Select p from Profile p where p.login = :login")
public class Profile {
  
  public static final String TAG = "profile";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_ID_GEN")
  @SequenceGenerator(name = "PROFILE_ID_GEN", sequenceName = "PROFILES_ID_SEQ", allocationSize = 100)
  private long id;
  
  @Version
  private long version;

  @Column(unique = true, nullable = false)
  private String login;

  @Column(nullable = false)
  private String password;

  private String firstName;

  private String lastName;

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

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public final int hashCode() {
    final int prime = 31;
    int result = 1;

    long id = id();
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
