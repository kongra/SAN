package san.algo.math;

import java.math.BigInteger;

public class Pow {

  public static BigInteger compute(long a, long n) {
    return compute(BigInteger.valueOf(a), n);
  }

  public static BigInteger compute(BigInteger a, long n) {
    BigInteger result = BigInteger.ONE;
    while (n != 0) {
      result = result.multiply(a);
      n -= 1;
    }
    return result;
  }

  public static BigInteger fast(long a, long n) {
    return fast(BigInteger.valueOf(a), n);
  }
  
  private static BigInteger fast(BigInteger a, long n) {
    if (n == 0) {
      return BigInteger.ONE;
    }
    else if (n % 2 == 0) {
      return square(fast(a, n / 2));
    }
    return a.multiply(fast(a, n - 1));
  }

  private static BigInteger square(BigInteger x) {
    return x.multiply(x);
  }

  // public static BigInteger compute(BigInteger a, long n) {
  // if (n == 0) {
  // return BigInteger.ONE;
  // }
  // return compute(a, n - 1).multiply(a);
  // }

}
