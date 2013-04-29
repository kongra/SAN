package san.jipp.math;

class RectangularComplex extends Complex {

  private final double real;

  private final double imag;

  RectangularComplex(double real, double imag) {
    this.real = real;
    this.imag = imag;
  }

  @Override
  public double argument() {
    return Math.atan2(imag(), real());
  }

  @Override
  public double imag() {
    return this.imag;
  }

  @Override
  public double modulus() {
    return Math.sqrt(real() * real() + imag() * imag());
  }

  @Override
  public double real() {
    return this.real;
  }

  @Override
  public String toString() {
    if (this.real != 0) {
      if (this.imag != 0) {
        // (real +- imagj)
        StringBuilder buf = new StringBuilder("(");
        buf.append(this.real);
        buf.append(this.imag < 0 ? "-" : "+");
        buf.append(Math.abs(this.imag)).append("j");
        return buf.append(")").toString();
      }
      // (real + 0j)
      return new StringBuilder("(").append(this.real).append("+0j)").toString();
    }
    if (this.imag != 0) {
      return new StringBuilder(this.imag < 0 ? "-" : "").append(
        Math.abs(this.imag)).append("j").toString();
    }
    return "0j";

  }

  @Override
  public Complex toPolar() {
    return Complex.polar(this.modulus(), this.argument());
  }

  @Override
  public Complex toRectangular() {
    return this;
  }

}
