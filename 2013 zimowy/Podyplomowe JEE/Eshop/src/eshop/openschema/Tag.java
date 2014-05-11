package eshop.openschema;

import java.util.Set;

public class Tag {

  public final String name;

  public final Set<Property> properties;

  private Tag(String name, Set<Property> properties) {
    this.name = name;
    this.properties = properties;
  }

}
