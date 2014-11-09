package san.math;

final class PlanarComplex extends Complex {

  private final double re;

  private final double im;

  PlanarComplex(double re, double im) {
    this.re = re;
    this.im = im;
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
    return Math.sqrt(re * re + im * im);
  }

  @Override
  public double angle() {
    return Math.atan2(im, re);
  }

  @Override
  public Complex add(Complex other) {
    return new PlanarComplex(this.re() + other.re(), this.im() + other.im());
  }

  @Override
  public Complex asPlanar() {
    return this;
  }

  @Override
  public Complex asPolar() {
    return new PolarComplex(this.modulus(), this.angle());
  }
  
}
