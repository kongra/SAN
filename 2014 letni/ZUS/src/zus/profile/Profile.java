package zus.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PROFILES")
@NamedQueries({
    @NamedQuery(name = "findProfileByLogin",   query = "Select p from Profile p where p.login = :login"),
    @NamedQuery(name = "findProfileIdByLogin", query = "Select p.id from Profile p where p.login = :login")
})
public class Profile {

  @Id
  private long id;

  @Column(unique = true)
  private String login;

  private String password;

  private String firstName;

  private String lastName;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public static final String TAG = "PROFILE";
}
