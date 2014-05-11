package san.algo;

import san.fn.Delay;
import san.fn.Nullary;
import san.fn.Unary;

public class Seqs {

  public static long count(ISeq s) {
    long result = 0;
    while (!s.isEmpty()) {
      result += 1;
      s = s.rest();
    }

    return result;
  }

  public static ISeq map(final Unary f, final ISeq s) {
    if (s == null || s.isEmpty()) {
      return EmptySeq.INSTANCE;
    }

    return LazySeq.of(f.call(s.first()), Delay.of(new Nullary() {
      @Override
      public Object call() {
        return Seqs.map(f, s.rest());
      }
    }));
  }
  
  public static void print(ISeq s) {
    while(!s.isEmpty()) {
      System.out.println(s.first());
      s = s.rest();
    }
  }
}
