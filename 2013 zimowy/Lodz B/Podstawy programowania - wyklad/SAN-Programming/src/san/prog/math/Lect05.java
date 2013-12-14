package san.prog.math;

import java.math.BigInteger;

public class Lect05 {

  public static void main(String[] args) {
    // Ratio x = new Ratio(1, 3);
    // Ratio y = new Ratio(1245, 1789);
    //
    // System.out.println(x.m);
    // System.out.println(y.n);
  }

  public static BigInteger pow(BigInteger base, int n) {
    BigInteger value = BigInteger.ONE;
    for (int i = 0; i < n; i++) {
      // value = value * base
      value = value.multiply(base);
    }
    return value;
  }

  public static BigInteger pow(long base, int n) {
    return pow(BigInteger.valueOf(base), n);
  }

  private static void test02() {
    System.out.println(pow(2, 0));
    System.out.println(pow(2, 3));
    System.out.println(pow(5, 8));

    System.out.println(pow(2, 10000));
  }

  private static void test01() {
    double jednatrzecia = 0.3333333333333333;
    System.out.println(jednatrzecia * 3);
    System.out.println(2.00 - 1.10);
  }

}
