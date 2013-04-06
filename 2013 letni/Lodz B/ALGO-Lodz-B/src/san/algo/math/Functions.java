package san.algo.math;

public class Functions {

  public static long factorial(long n) {
    if (n == 0) {
      return 1;
    }
    return n * factorial(n - 1);
  }

}
