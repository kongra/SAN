package san.coll;

import san.fn.Binary;
import san.fn.Nullary;
import san.fn.UnaryBody;

public class Utils {

  public static String toString(ISeq coll) {
    final StringBuilder result = new StringBuilder("(");

    // ISeq s = coll;
    // while (s != ISeq.NULL) {
    // result.append(s.first());
    // s = s.rest();
    // if (s != ISeq.NULL) {
    // result.append(", ");
    // }
    // }

    doseq(coll, new UnaryBody() {
      @Override
      public void run(Object element) {
        result.append(element);
      }
    });

    return result.append(")").toString();
  }

  public static void doseq(ISeq coll, UnaryBody body) {
    ISeq s = coll;
    while (s != ISeq.NULL) {
      body.run(s.first());
      s = s.rest();
    }
  }

  public static ISeq take(final int n, final ISeq coll) {
    if (coll == ISeq.NULL || 0 == n) {
      return ISeq.NULL;
    }

    return LazySeq.create(coll.first(), new Nullary() {
      @Override
      public Object call() {
        return take(n - 1, coll.rest());
      }
    });
  }

  public static ISeq integersAscending(final long n) {
    return LazySeq.create(n, new Nullary() {
      @Override
      public Object call() {
        return integersAscending(n + 1);
      }
    });
  }

  public static Object reduce(Binary f, ISeq coll) {
    if (coll == ISeq.NULL) {
      return null;
    }
    return reduce(f, coll.first(), coll.rest());
  }

  public static Object reduce(Binary f, Object start, ISeq coll) {
    Object result = start;
    ISeq s = coll;
    while (s != ISeq.NULL) {
      result = f.call(result, s.first());
      s = s.rest();
    }
    return result;
  }

  public static ISeq naturals() {
    return integersAscending(0);
  }

  private Utils() {
    ;
  }
}
