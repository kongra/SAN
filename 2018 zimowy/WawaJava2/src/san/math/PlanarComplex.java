package san.math;

class PlanarComplex extends Complex {

  private final double re;

  private final double im;

  PlanarComplex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  @Override
  public double re() {
    return this.re;
  }

  @Override
  public double im() {
    return this.im;
  }

  @Override
  public double modulus() {
    return Math.sqrt(this.re * this.re + this.im * this.im);
  }

  @Override
  public double argument() {
    return Math.atan2(this.im, this.re);
  }

  @Override
  public String toString() {
    if (this.re() == 0.0) {
      return this.im() + "j";
    } else {
      return "(" + this.re() + "+" + this.im() + "j)";
    }
  }

  @Override
  public Complex asPlanar() {
    return this;
  }

  @Override
  public Complex asPolar() {
    return Complex.polar(this.modulus(), this.argument());
  }
}
