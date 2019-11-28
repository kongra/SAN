package san.math;

public class Complex {

  final double re;

  final double im;

  Complex(double re) {
    this(re, 0);
  }

  Complex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  public static String repr(Complex c) {
    String op = c.im < 0 ? "-" : "+";
    String re = c.re == 0 ? "" : String.valueOf(c.re);
    if (c.re == 0 && c.im >= 0) {
      op = "";
    }
    return new StringBuilder("(")
        .append(re)
        .append(op)
        .append(Math.abs(c.im))
        .append("j)").toString();
  }
}
