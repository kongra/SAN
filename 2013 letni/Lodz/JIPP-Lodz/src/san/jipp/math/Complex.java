package san.jipp.math;

public class Complex {

  public final double real;

  public final double imag;

  public Complex(double real, double imag) {
    this.real = real;
    this.imag = imag;
  }

  public Complex(double value) {
    this(value, 0);
  }

  public Complex add(Complex other) {
    return new Complex(this.real + other.real, this.imag + other.imag);
  }

  public Complex add(double value) {
    return add(new Complex(value));
  }

  @Override
  public String toString() {
    if (this.real != 0) {
      if (this.imag != 0) {
        // (real +- imagj)
        StringBuilder buf = new StringBuilder("(");
        buf.append(this.real);
        buf.append(this.imag < 0 ? "-" : "+");
        buf.append(abs(this.imag)).append("j");
        return buf.append(")").toString();
      }
      // (real + 0j)
      return new StringBuilder("(").append(this.real).append("+0j)").toString();
    }
    if (this.imag != 0) {
      return new StringBuilder(this.imag < 0 ? "-" : "")
          .append(abs(this.imag)).append("j").toString();
    }
    return "0j";

  }

  private static double abs(double x) {
    return x < 0 ? -x : x;
  }

}
