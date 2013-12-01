package san.math;

public class Utils {

  public static double sumReals(Complex... nums) {
    double sum = 0;
    for (Complex x : nums) {
      sum += x.re();
    }
    return sum;
  }

}
