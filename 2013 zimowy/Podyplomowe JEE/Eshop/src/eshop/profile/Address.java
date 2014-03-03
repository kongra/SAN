package eshop.profile;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESSES_SEQ")
  @SequenceGenerator(name = "ADDRESSES_SEQ", allocationSize = 100, sequenceName = "ADDRESSES_SEQ")
  private long id;

  private String street;

  private String number;

  private String code;

  private String city;

  private String country;

  @OneToMany
  private Set<Profile> profiles;

  public long id() {
    return id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Set<Profile> getProfiles() {
    return profiles;
  }

  public void setProfiles(Set<Profile> profiles) {
    this.profiles = profiles;
  }

}
