package san.pp;

public class Vectors {

  public static double product(double[] x, double[] y) {
    assertDimensions(x, y);
    double result = 0;
    for (int i = 0; i < x.length; i++) {
      result += x[i] * y[i];
    }
    return result;
  }

  public static void assertDimensions(double[] x, double[] y) {
    if (x.length != y.length) {
      throw new IllegalArgumentException("Arguments have different dimensions " +
        x.length + " and " + y.length);
    }
  }

  public static double normE(double[] x) {
    return Math.sqrt(product(x, x));
  }

  public static double cos(double[] v, double[] w) {
    assertDimensions(v, w);
    return product(v, w) / (normE(v) * normE(w));
  }

  public static void main(String[] args) {
    double[] v1 = { 1, 2, 3, 4, 5};
    double[] v2 = { 6, 7, 8, 9, 10 };
    double x = product(v2, v1);
    System.out.println(x);
    System.out.println(cos(v1, v2));

    long start = System.nanoTime();
    for (int i = 0; i < 1e9; i++) {
      cos(v1, v2);
    }
    long end = System.nanoTime();

    System.out.println("Elapsed time [msecs] is " + ((end - start) / 1e6));

  }

}
