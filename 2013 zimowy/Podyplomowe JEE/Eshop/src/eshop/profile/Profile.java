package eshop.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "PROFILES")
@NamedQuery(name = "findProfileByLogin", query = "Select p from Profile p where p.login = :login")
public class Profile {

  // @Id
  // @GeneratedValue(strategy = GenerationType.TABLE, generator =
  // "PROFILES_ID_TAB")
  // @TableGenerator(name = "PROFILES_ID_TAB", table = "SEQUENCE_TABLE",
  // pkColumnName = "SEQ_NAME", valueColumnName = "COUNT",
  // pkColumnValue = "PROFILES_ID_TAB", allocationSize = 100)

  // @Id
  // @GeneratedValue(strategy=GenerationType.IDENTITY)

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILES_SEQ")
  @SequenceGenerator(name = "PROFILES_SEQ", allocationSize = 100, sequenceName = "PROFILES_SEQ")
  private long id;

  @Column(unique = true)
  private String login;

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

}
