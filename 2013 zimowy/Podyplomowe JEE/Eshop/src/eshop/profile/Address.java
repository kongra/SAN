package eshop.profile;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ADDRESSES")
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
  @JoinTable(name = "ADDRESSES_B2BS")
  private Set<B2B> b2bs;

  @OneToMany
  @JoinTable(name = "ADDRESSES_B2CS")
  private Set<B2C> b2cs;

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

  public Set<B2B> getB2bs() {
    return b2bs;
  }

  public void setB2bs(Set<B2B> b2bs) {
    this.b2bs = b2bs;
  }

  public Set<B2C> getB2cs() {
    return b2cs;
  }

  public void setB2cs(Set<B2C> b2cs) {
    this.b2cs = b2cs;
  }

}
