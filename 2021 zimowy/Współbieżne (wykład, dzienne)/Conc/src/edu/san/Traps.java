package edu.san;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Traps {

  static boolean isOdd(int n) {
    // return n % 2 == 1;
    return !isEven(n);
  }

  static boolean isEven(int n) {
    // return n % 2 == 0;
    return (n & 1) == 0;
  }

  //  public static void main(String... args) {
  //    for (int i = -9; i < 10; i++) {
  //      System.out.println(i + " : " + isOdd(i) + ", " + (i % 2));
  //    }
  //  }

  static boolean areEqual(double x, double y) {
    return x == y;
  }

  static boolean areEqual7(double x, double y) {
    return Math.abs(x - y) < 1.0 / 1e7;
  }

  public static void main(String... args) {
    // double x = 1 / 0; // (double) (int / int)
    // double x = 1.0 / 0.0;
    // double x = 1.0 / 0; // double / (double)int
    // double x = 1 / 0.0; // (double)int / double

    double x = 2.0;
    double y = 1.1;
    double z = x - y; // 0.9

    System.out.printf("%.2f%n", z);
    System.out.println(z);

    double w = 1.0 / 3.0;

    // long w1 = 333333;

    long l1 = 1000;
    long l2 = 3000;

    System.out.println(areEqual7(z, 0.9));

    BigDecimal x1 = new BigDecimal("2.0");
    BigDecimal y1 = new BigDecimal("1.1");
    BigDecimal z1 = x1.subtract(y1);

    System.out.println(z1.equals(new BigDecimal("0.9")));

    z1.divide(y1, RoundingMode.HALF_DOWN);
  }


}
