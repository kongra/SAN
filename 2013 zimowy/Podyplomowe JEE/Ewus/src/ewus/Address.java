package ewus;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {

  @Id
  private long id;

  private String street;

  private String number;

  private String code;

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

  public long getId() {
    return id;
  }

}
