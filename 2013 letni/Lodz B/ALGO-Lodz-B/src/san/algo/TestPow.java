package san.algo;

import java.math.BigInteger;

import san.algo.math.Pow;

public class TestPow {

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      testFastPow();
    }

    // System.out.println(val.toString().length());
  }

  private static void testFastPow() {
    long start = System.currentTimeMillis();
    String s = Pow.fast(2, 1000000).toString();
    long end = System.currentTimeMillis();
    System.out.println("Finished in " + (end - start) + " msecs.");
  }

}
