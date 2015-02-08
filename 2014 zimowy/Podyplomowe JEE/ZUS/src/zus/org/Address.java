package zus.org;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Address {

  @EmbeddedId
  private AddressKey key;
  
}
