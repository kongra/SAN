package zus.util;

import java.util.Collection;

public class Coll {

  public static <T> T first(Collection<T> coll, T notFound) {
    if (coll == null || coll.isEmpty()) {
      return notFound;
    }
    return coll.iterator().next();
  }

  private Coll() {

  }

}
