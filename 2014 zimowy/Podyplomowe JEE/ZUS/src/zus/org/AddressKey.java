package zus.org;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class AddressKey implements Serializable {

  private String street;

  private String city;

  public AddressKey() {
  }

  public AddressKey(String street, String city) {
    this.street = street;
    this.city = city;
  }

  @Override
  public final int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((street == null) ? 0 : street.hashCode());
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
    if (getClass() != obj.getClass()) {
      return false;
    }
    AddressKey other = (AddressKey) obj;
    if (city == null) {
      if (other.city != null) {
        return false;
      }
    }
    else if (!city.equals(other.city)) {
      return false;
    }
    if (street == null) {
      if (other.street != null) {
        return false;
      }
    }
    else if (!street.equals(other.street)) {
      return false;
    }
    return true;
  }

  
}
