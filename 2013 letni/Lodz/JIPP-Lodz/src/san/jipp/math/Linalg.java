package san.jipp.math;

public class Linalg {

  public static double[] mutl(double[][] a, double[] x) {
    final int m = a.length;
    final int n = a[0].length;
    final double[] result = new double[m];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        result[i] += a[i][j] * x[j];
      }
    }

    return result;
  }

  public static double scalarMult(double[] x1, double[] x2) {
    double value = 0;
    for (int i = 0; i < x1.length; i++) {
      value += x1[i] * x2[i];
    }
    return value;
  }

  public static boolean areOrthogonal(double[] x1, double[] x2) {
    return scalarMult(x1, x2) == 0.0;
  }

}
