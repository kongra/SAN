package san.algo.math;

public class Heron {

  public static double sqrt(double x) {
    return sqrt(1, x);
  }

  private static double sqrt(double y, double x) {
    if (isEnough(y, x)) {
      return y;
    }
    return sqrt(better(y, x), x);
  }

  private static double better(double y, double x) {
    return (y + x / y) / 2.0;
  }

  private static boolean isEnough(double y, double x) {
    return Math.abs(y * y - x) <= 0.000001;
  }

}
