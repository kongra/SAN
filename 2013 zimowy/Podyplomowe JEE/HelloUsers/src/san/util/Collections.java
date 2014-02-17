package san.util;

import java.util.Collection;
import java.util.List;

public class Collections {

  public static <T> void addAll(Collection<? extends T> src, List<? super T> dst) {
    for (T p : src) {
      dst.add(p);
    }
  }

  private Collections() {
    ;
  }
}
