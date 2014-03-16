package eshop.utils;

import java.util.Collection;

public class Coll {

  public static <T> T first(Collection<T> coll, T notFound) {
    if (null == coll || coll.isEmpty()) {
      return notFound;
    }
    return coll.iterator().next();
  }

}
