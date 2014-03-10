package zus.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PROFILES")
@NamedQueries({
    @NamedQuery(name = "findProfileByLogin", query = "Select p from Profile p where p.login = :login"),
    @NamedQuery(name = "findProfileIdByLogin", query = "Select p.id from Profile p where p.login = :login")
})
public class Profile {

  // @GeneratedValue(strategy=GenerationType.IDENTITY)

  // @GeneratedValue(strategy = GenerationType.TABLE, generator =
  // "PROFILES-ID-GEN")
  // @TableGenerator(name = "PROFILES-ID-GEN",
  // allocationSize = 100,
  // table = "ID_SEQUENCES",
  // pkColumnName = "TYPE",
  // pkColumnValue = "PROFILE",
  // valueColumnName = "VALUE")

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILES-ID-GEN")
  @SequenceGenerator(name = "PROFILES-ID-GEN", allocationSize = 100, sequenceName = "PROFILES_ID_SEQ")
  private long id;

  @Column(unique = true)
  private String login;

  private String password;

  private String firstName;

  private String lastName;

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

  public static final String TAG = "PROFILE";
}
