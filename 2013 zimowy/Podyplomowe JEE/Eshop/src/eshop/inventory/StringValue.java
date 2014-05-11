package eshop.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findStringValue", query = "Select v from StringValue v where v.strValue = :value")
public class StringValue extends PropValue {

  @Column(unique = true)
  private String strValue;

  public StringValue() {
  }

  public StringValue(String strValue) {
    this.strValue = strValue;
  }

  @Override
  public Object value() {
    return strValue;
  }

}
