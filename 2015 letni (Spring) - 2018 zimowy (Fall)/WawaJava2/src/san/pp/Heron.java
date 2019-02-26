package san.pp;

public class Heron {

  public static void main(String[] args) {
    System.out.println(loop(25, 1));
  }

  private static double loop(double x, double y) {
    if (goodEnough(y, x)) {
      return y;
    }
    return loop(x, improved(y, x));
  }

  private static double improved(double y, double x) {
    return (y + x / y) / 2;
  }

  private static boolean goodEnough(double y, double x) {
    return abs(square(y) - x) < 0.000001;
  }

  private static double square(double x) {
    return x * x;
  }

  private static double abs(double x) {
    return x < 0 ? -x : x;
  }
}
