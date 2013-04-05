package san.jipp.math;

public class Complex {

  public final double re;

  public final double im;

  public Complex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  public String repr() {
    if (re != 0) {
      if (im != 0) {
        // re + im j
        return new StringBuilder().append("(").append(re).append("+")
            .append(im).append("j").append(")").toString();
      }
      // re
      return new StringBuilder().append("(").append(re).append("+0.0j)")
          .toString();
    }
    if (im != 0) {
      return String.valueOf(im) + "j";
    }
    return "0.0j";
  }

  public Complex add(Complex other) {
    return new Complex(this.re + other.re, this.im + other.im);
  }

}
