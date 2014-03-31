package allegro.util;

import java.util.Collection;

public class Coll {

  public static <T> T first(Collection<T> coll, T whenEmpty) {
    if (coll == null || coll.isEmpty()) {
      return whenEmpty;
    }
    return coll.iterator().next();
  }

}
