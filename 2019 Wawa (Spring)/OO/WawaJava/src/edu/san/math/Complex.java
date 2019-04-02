package edu.san.math;

public class Complex {

  double re;

  double im;

  public Complex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  public double modulus() {
    return Math.sqrt(
        square(this.re) +
        square(this.im));
  }

  public Complex add(Complex other) {
    return new Complex(
        this.re + other.re,
        this.im + other.im);
  }

  public String asString() {
    StringBuilder repr = new StringBuilder();
    if (this.re == 0) {
      repr.append(this.im).append("j");
    } else {
      repr.append("(");
      repr.append(this.re);
      if (this.im < 0) {
        repr.append("-").append(Math.abs(this.im));
      } else {
        repr.append("+").append(this.im);
      }
      repr.append("j)");
    }
    return repr.toString();
  }

  private double square(double x) {
    return x * x;
  }

}
