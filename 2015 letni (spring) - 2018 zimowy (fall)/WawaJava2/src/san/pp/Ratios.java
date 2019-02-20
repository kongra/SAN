package san.pp;

public class Ratios {

  public static Object cons(long n, long d) {
    if (d < 0) {
      n = -n;
      d = -d;
    }
    long g = gcd(Math.abs(n), Math.abs(d));
    return new long[]{n / g, d / g};
  }

  public static long num(Object x) {
    long[] r = (long[])x;
    return r[0];
  }

  public static long denom(Object x) {
    long[] r = (long[])x;
    return r[1];
  }

  public static String toString(Object x) {
    long d = denom(x);
    return d == 1 ? String.valueOf(num(x)) : num(x) + "/" + denom(x);
  }

  public static Object add(Object x, Object y) {
    long n1 = num(x);
    long d1 = denom(x);
    long n2 = num(y);
    long d2 = denom(y);

    long n = n1 * d2 + n2 * d1;
    long d = d1 * d2;

    return cons(n, d);
  }

  public static double toDouble(Object x) {
    double n = num(x);
    double d = denom(x);
    return n / d;
  }

  public static long gcd(long m, long n) {
    long r = m % n;
    return r == 0 ? n : gcd(n, r);
  }
}
