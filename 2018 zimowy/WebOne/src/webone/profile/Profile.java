package webone.profile;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@SequenceGenerator(name = "ProfileSeq", initialValue = 1, allocationSize = 100, sequenceName = "ProfileSeq")
public class Profile implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProfileSeq")
  private long id;

  @Column(length = 64, nullable = false, unique = true)
  private String login;

  @Column(length = 64, nullable = false)
  private String passwd;

  private String email;

  @ManyToOne
  private Address address;

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

}
