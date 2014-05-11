package eshop.openschema;

import java.util.Map;

public class Item {

  public final Tag tag;

  public final Map<Property, Item> values;

  private Item(Tag tag, Map<Property, Item> values) {
    this.tag = tag;
    this.values = values;
  }

}
