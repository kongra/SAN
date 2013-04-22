package san.algo.math;

public class Peano {

  public static long inc(long n) {
    return n + 1;
  }

  public static long dec(long n) {
    return n - 1;
  }

  public static long plus(long x, long y) {
    if (x == 0) {
      return y;
    }
    return plus(dec(x), inc(y));
  }

  public static void main(String... args) {

    System.out.println(Peano.plus(3, 4));

  }
}
