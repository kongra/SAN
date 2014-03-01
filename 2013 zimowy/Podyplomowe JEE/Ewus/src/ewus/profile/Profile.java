package ewus.profile;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "PROFILES")
@Access(AccessType.FIELD)
@NamedQuery(name = "findProfileByLogin", query = "Select p from Profile p where p.login = :login")
public class Profile implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_ID_GEN")
  @SequenceGenerator(name = "PROFILE_ID_GEN", sequenceName = "PROFILE_ID_SEQ")
  private long id;

  @Version
  private long version;

  private String firstName;

  private String lastName;

  @Column(unique = true)
  private String login;

  private String password;

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

  public long getId() {
    return id;
  }

  private static final long serialVersionUID = 1L;

}
