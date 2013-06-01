package san.jipp.util;

public class Func {

  public interface Binary {

    Object call(Object lhs, Object rhs);

  }

  public static final Binary IntegerPLUS = new Binary() {
    @Override
    public Object call(Object lhs, Object rhs) {
      Integer a = (Integer) lhs;
      Integer b = (Integer) rhs;
      return a + b;
    }
  };

  public static Object reduce(Seq coll, Binary f, Object init) {
    if (coll.isEmpty()) {
      return init;
    }
    return reduce(coll.rest(), f, f.call(init, coll.first()));
  }

}
