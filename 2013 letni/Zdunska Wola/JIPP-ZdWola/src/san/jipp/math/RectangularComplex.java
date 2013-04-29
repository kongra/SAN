package san.jipp.math;

class RectangularComplex extends Complex {

  private final double re;

  private final double im;

  RectangularComplex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  @Override
  public double argument() {
    return Math.atan2(im(), re());
  }

  @Override
  public double im() {
    return this.im;
  }

  @Override
  public double modulus() {
    return Math.sqrt(re() * re() + im() * im());
  }

  @Override
  public double re() {
    return this.re;
  }

  @Override
  public Complex toPolar() {
    return Complex.polar(modulus(), argument());
  }

  @Override
  public Complex toRectangular() {
    return this;
  }

  @Override
  public String toString() {
    if (this.re() != 0) {
      if (this.im() != 0) {
        // (real +- imagj)
        StringBuilder buf = new StringBuilder("(");
        buf.append(this.re());
        buf.append(this.im() < 0 ? "-" : "+");
        buf.append(Math.abs(this.im())).append("j");
        return buf.append(")").toString();
      }
      // (real + 0j)
      return new StringBuilder("(").append(this.re()).append("+0j)").toString();
    }
    if (this.im() != 0) {
      return new StringBuilder(this.im() < 0 ? "-" : "").append(
        Math.abs(this.im())).append("j").toString();
    }
    return "0j";

  }

  @Override
  public Complex add(Complex other) {
    return Complex.rectangular(this.re() + other.re(), this.im() + other.im());
  }

}
