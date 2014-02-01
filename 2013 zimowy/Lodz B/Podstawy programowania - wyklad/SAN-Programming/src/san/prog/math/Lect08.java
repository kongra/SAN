package san.prog.math;

import java.math.BigDecimal;

public class Lect08 {

  public static boolean areEqual(double d, float f) {
    return Double.valueOf(Float.toString(f)) == d;
  }

  public static boolean areEqual(double x, double y) {
    return Double.compare(x, y) == 0;
  }

  public static boolean isItMe(double x) {
    return areEqual(x, x);
  }

  public static void main(String[] args) {
     Complex a = new Complex(1, -1);
     Complex b = new Complex(3, 4);
     Complex c = new Complex(3, 4);
     Complex d = a;
     
     System.out.println(a == b);
     System.out.println(b == c);
     System.out.println(a == d);
     
//    double x = 0.0 / 0.0;
//    System.out.println(isItMe(x));

    // long l = 0 / 0;


    // int i = 10;
    // long j = i;
    // System.out.println(i == j);

    // float f = 1.234f;
    // double d = 1.234;
    //
    // BigDecimal bd = new BigDecimal(f);
    // System.out.println(bd);

    // double d = 1.234;
    // float f = (float) d;

    // System.out.println(areEqual(1.234, 1.234f));
    // System.out.println(isItMe(Double.NaN));
  }

}
