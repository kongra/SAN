package san.math;

public class Fib {

  public static long fib(long n) {
    if (n == 0 || n == 1) {
      return n;
    }
    return fib(n - 1) + fib(n - 2);
  }

  public static FibCount fibcount(long n) {
    if (n == 0 || n == 1) {
      return new FibCount(n, 1);
    }
    FibCount c1 = fibcount(n - 1);
    FibCount c2 = fibcount(n - 2);
    return new FibCount(c1.value + c2.value, c1.count + c2.count);
  }
}
