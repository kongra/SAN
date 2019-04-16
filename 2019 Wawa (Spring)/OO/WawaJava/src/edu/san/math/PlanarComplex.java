package edu.san.math;

public class PlanarComplex implements Complex {

  private final double re;

  private final double im;

  PlanarComplex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  @Override
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

  @Override
  public double re() {
    return re;
  }

  @Override
  public double im() {
    return im;
  }

  @Override
  public double modulus() {
    return Math.sqrt(
        square(this.re) +
            square(this.im));
  }

  @Override
  public double argument() {
    return Math.atan2(im(), re());
  }

  @Override
  public Complex asPolar() {
    return Complex.polar(modulus(), argument());
  }

  @Override
  public Complex asPlanar() {
    return this;
  }

  private double square(double x) {
    return x * x;
  }

}
