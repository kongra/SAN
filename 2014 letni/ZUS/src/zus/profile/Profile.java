package zus.profile;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Inheritance
@DiscriminatorColumn(name="PROFILE_TYPE")
@Table(name = "PROFILES")
@NamedQueries({
    @NamedQuery(name = "findProfileByLogin", query = "Select p from Profile p where p.login = :login"),
    @NamedQuery(name = "findProfileIdByLogin", query = "Select p.id from Profile p where p.login = :login")
})
public abstract class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILES-ID-GEN")
  @SequenceGenerator(name = "PROFILES-ID-GEN", allocationSize = 100, sequenceName = "PROFILES_ID_SEQ")
  private long id;

  @Version
  private long version;

  @Column(unique = true)
  private String login;

  private String password;

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
