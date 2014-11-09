package san.math;

final class PolarComplex extends Complex {

  private final double modulus;

  private final double angle;

  PolarComplex(double modulus, double angle) {
    this.modulus = modulus;
    this.angle = angle;
  }

  @Override
  public double re() {
    return modulus * Math.cos(angle);
  }

  @Override
  public double im() {
    return modulus * Math.sin(angle);
  }

  @Override
  public double modulus() {
    return modulus;
  }

  @Override
  public double angle() {
    return angle;
  }

  @Override
  public Complex add(Complex other) {
    return this.asPlanar().add(other).asPolar();
  }

  @Override
  public Complex asPlanar() {
    return new PlanarComplex(this.re(), this.im());
  }

  @Override
  public Complex asPolar() {
    return this;
  }

}
