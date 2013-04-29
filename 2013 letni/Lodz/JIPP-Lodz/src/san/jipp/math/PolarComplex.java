package san.jipp.math;

class PolarComplex extends Complex {

  private final double modulus;

  private final double argument;

  PolarComplex(double modulus, double argument) {
    this.modulus = modulus;
    this.argument = argument;
  }

  @Override
  public double argument() {
    return this.argument;
  }

  @Override
  public double imag() {
    return modulus() * Math.sin(argument());
  }

  @Override
  public double modulus() {
    return this.modulus;
  }

  @Override
  public double real() {
    return modulus() * Math.cos(argument());
  }

  @Override
  public String toString() {
    return "(" + modulus() + "∠" + argument() + ")";
  }

  @Override
  public Complex toPolar() {
    return this;
  }

  @Override
  public Complex toRectangular() {
    return Complex.rectangular(this.real(), this.imag());
  }

  @Override
  public Complex add(Complex other) {
    return super.add(other).toPolar();
  }

}
