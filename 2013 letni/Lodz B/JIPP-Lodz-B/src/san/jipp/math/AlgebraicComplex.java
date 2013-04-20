package san.jipp.math;

class AlgebraicComplex extends Complex {

  private final double re;

  private final double im;

  AlgebraicComplex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  @Override
  public double argument() {
    if (this.re() > 0) {
      return Math.atan(this.im() / this.re());
    }
    else if (this.re() < 0 && this.im() >= 0) {
      return Math.atan(this.im() / this.re()) + Math.PI;
    }
    else if (this.re() < 0 && this.im() < 0) {
      return Math.atan(this.im() / this.re()) - Math.PI;
    }
    else if (this.re() == 0 && this.im() > 0) {
      return Math.PI / 2;
    }
    else if (this.re() == 0 && this.im() < 0) {
      return -Math.PI / 2;
    }
    return Double.NaN;
  }

  @Override
  public double im() {
    return this.im;
  }

  @Override
  public double modulus() {
    return Math.sqrt(this.re() * this.re() + this.im() * this.im());
  }

  @Override
  public double re() {
    return this.re;
  }

  @Override
  public Complex algebraic() {
    return this;
  }

  @Override
  public Complex polar() {
    return Complex.polar(this.modulus(), this.argument());
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
}
