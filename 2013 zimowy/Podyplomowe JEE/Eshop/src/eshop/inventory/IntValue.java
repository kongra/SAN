package eshop.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findIntValue", query = "Select v from IntValue v where v.intValue = :value")
public class IntValue extends PropValue {

  @Column(unique = true)
  private long intValue;

  public IntValue() {
  }

  public IntValue(long intValue) {
    this.intValue = intValue;
  }

  @Override
  public Object value() {
    return intValue;
  }

}
