package webone.profile;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "AddressSeq", initialValue = 1, allocationSize = 100, sequenceName = "AddressSeq")
public class Address implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AddressSeq")
  private long id;

  private String city;

  private String street;

  private String postalCode;

  private String number;

  @OneToMany
  private Set<Profile> profiles;
  
  public Address() {
    super();
  }

  public Address(String city, String street, String postalCode, String number) {
    super();
    this.city = city;
    this.street = street;
    this.postalCode = postalCode;
    this.number = number;
    this.profiles = new HashSet<>();
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Set<Profile> getProfiles() {
    return profiles;
  }

  public long getId() {
    return id;
  }

}
