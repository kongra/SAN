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

  public static ISeq map(Unary f, ISeq coll) {
    ISeq result = LinkedSeq.EMPTY;
    ISeq seq = coll;
    while (!seq.isEmpty()) {
      result = result.cons(f.call(seq.first()));
      seq = seq.rest();
    }

    return result;
  }

  public static Object reduce(Binary f, Object start, ISeq coll) {
    Object value = start;
    while (!coll.isEmpty()) {
      value = f.call(value, coll.first());
      coll = coll.rest();
    }

    return value;
  }

  private Utils() {
    ;
  }

}
