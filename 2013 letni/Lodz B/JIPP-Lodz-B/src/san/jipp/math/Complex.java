package san.jipp.math;

public class Complex {

  public final double re;

  public final double im;

  public Complex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  public Complex(double other) {
    this(other, 0);
  }

  public Complex add(Complex other) {
    return new Complex(this.re + other.re, this.im + other.im);
  }

  public Complex add(double other) {
    return add(new Complex(other));
  }

  public void print() {
    StringBuilder buf = new StringBuilder("C(");

    if (this.re != 0.0) {
      buf.append(this.re);
      if (this.im != 0.0) {
        buf.append(" + ").append(this.im).append("i");
      }
    }
    else if (this.im != 0.0) {
      buf.append(this.im).append("i");
    }

    buf.append(")");
    System.out.println(buf.toString());
  }
}
