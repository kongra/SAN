package san.jipp.math;

public class Algebra {

  public static double scalar(double[] x, double[] y) {
    double result = 0;

    int n = min(x.length, y.length);

    for (int i = 0; i < n; i++) {
      result += x[i] * y[i];
    }

    return result;
  }

  public static int min(int x, int y) {
    return x < y ? x : y;
  }

}
