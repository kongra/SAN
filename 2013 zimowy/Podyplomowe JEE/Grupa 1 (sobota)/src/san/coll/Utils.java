package san.coll;

import san.coll.fn.Binary;
import san.coll.fn.NoArg;
import san.coll.fn.Unary;

public class Utils {

  public static final Unary INC = new Unary() {
    @Override
    public Object call(Object param) {
      Integer i = (Integer) param;
      return i + 1;
    }
  };

  public static final Binary PLUS = new Binary() {
    @Override
    public Object call(Object lhs, Object rhs) {
      return (Integer) lhs + (Integer) rhs;
    }
  };

  public static void doSeq(final ISeq coll, final Unary body) {
    ISeq s = coll;
    while (!s.isEmpty()) {
      body.call(s.first());
      s = s.rest();
    }
  }

  public static void doSeq(final ISeq coll, long maxSteps, final Unary body) {
    ISeq s = coll;
    long count = 0;
    while (!s.isEmpty() && count != maxSteps) {
      body.call(s.first());
      s = s.rest();
      count += 1;
    }
  }

  public static ISeq integers(final int start) {
    return LazySeq.create(start, new NoArg() {
      @Override
      public Object call() {
        return integers(start + 1);
      }
    });
  }

  public static ISeq map(final Unary f, final ISeq coll) {
    if (coll.isEmpty()) {
      return LinkedSeq.EMPTY;
    }

    return LazySeq.create(f.call(coll.first()), new NoArg() {
      @Override
      public Object call() {
        return map(f, coll.rest());
      }
    });
  }

  public static Object reduce(Binary f, Object start, ISeq coll) {
    Object value = start;
    while (!coll.isEmpty()) {
      value = f.call(value, coll.first());
      coll = coll.rest();
    }

    return value;
  }

  public static ISeq interpose(final Object separator, final ISeq coll) {
    // EMPTY ONE - KEEP IT
    if (coll.isEmpty()) {
      return LinkedSeq.EMPTY;
    }

    // SINGLETON - KEEP IT
    if (coll.rest().isEmpty()) {
      return coll;
    }

    return LazySeq.create(coll.first(), new NoArg() {
      @Override
      public Object call() {
        return LazySeq.create(separator, new NoArg() {
          @Override
          public Object call() {
            return interpose(separator, coll.rest());
          }
        });
      }
    });
  }

  public static String toString(ISeq coll) {
    final StringBuilder buf = new StringBuilder("(");

    Utils.doSeq(coll.interpose(", "), new Unary() {
      @Override
      public Object call(Object element) {
        buf.append(element);
        return null;
      }
    });

    return buf.append(")").toString();
  }

  public static ISeq repeat(final Object obj) {
    return LazySeq.create(obj, new NoArg() {
      @Override
      public Object call() {
        return repeat(obj);
      }
    });
  }

  public static ISeq repeatedly(final NoArg f) {
    return LazySeq.create(f.call(), new NoArg() {
      @Override
      public Object call() {
        return repeatedly(f);
      }
    });
  }

  private Utils() {
    ;
  }

}
