package san.math;

import san.fn.Memo;
import san.fn.Unary;
import san.util.Timeit;

public class Fibonacci {

  public static long call(long n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1) {
      return 1;
    }
    return call(n - 1) + call(n - 2);
  }

  static final Unary fibo = Memo.memoize(new Unary() {
    @Override
    public Object call(Object arg) {
      long n = (Long) arg;
      if (n == 0) {
        return 0L;
      }
      if (n == 1) {
        return 1L;
      }
      return (Long) fibo.call(n - 1) + (Long) fibo.call(n - 2);
    }
  });
  
  public static void main(String... args) {
    for (int i = 0; i < 45; i++) {
      final long n = i;
      Timeit.printingElapsedTime(new Runnable() {
        @Override
        public void run() {
          System.out.println(fibo.call(n));
        }
      });
    }
  }
}
