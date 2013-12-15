package san.fn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Memo {

  // private final Map<Object, Object> data = new HashMap<Object, Object>();

  public static Binary memoize(final Binary f) {
    final Map<List<Object>, Object> cache = new HashMap<>();
    return new Binary() {
      @Override
      public Object call(Object lhs, Object rhs) {
        List<Object> key = Arrays.asList(lhs, rhs);
        Object value = cache.get(key);
        if (null != value) {
          return value;
        }

        value = f.call(lhs, rhs);
        cache.put(key, value);
        return value;
      }
    };
  }

  public static Unary memoize(final Unary f) {
    final Map<Object, Object> cache = new HashMap<>();
    return new Unary() {
      @Override
      public Object call(Object arg) {
        Object value = cache.get(arg);
        if (null != value) {
          return value;
        }

        value = f.call(arg);
        cache.put(arg, value);
        return value;
      }
    };
  }
}
