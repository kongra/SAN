class Heron {

  static double square(double x) {
    return x * x;
  }

  static double abs(double x) {
    if (x < 0) {
      return -x;
    } else {
      return x;
    }
  }

  static boolean isGoodEnough(double y, double x) {
    return abs(square(y) - x) <= 0.0001;
  }

  static double avg(double x, double y) {
    return (x + y) / 2.0;
  }

  static double enhance(double y, double x) {
    return avg(y, x / y);
  }

  static double sqrt(double y, double x) {
    if (isGoodEnough(y, x)) {
      return y;
    } else {
      return sqrt(enhance(y, x), x);
    }
  }

  public static void main(String[] args) {
    System.out.println(sqrt(1, 2));
  }

}
