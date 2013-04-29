package san.jipp.math;

class PolarComplex extends Complex {

  private final double modulus;

  private final double argument; // theta

  PolarComplex(double modulus, double argument) {
    this.modulus = modulus;
    this.argument = argument;
  }

  @Override
  public double argument() {
    return this.argument;
  }

  @Override
  public double im() {
    return modulus() * Math.sin(argument());
  }

  @Override
  public double modulus() {
    return this.modulus;
  }

  @Override
  public double re() {
    return modulus() * Math.sin(argument());
  }

  @Override
  public Complex toPolar() {
    return this;
  }

  @Override
  public Complex toRectangular() {
    return Complex.rectangular(re(), im());
  }

  @Override
  public String toString() {
    return "(" + modulus() + "∠" + argument() + ")";
  }

  @Override
  public Complex add(Complex other) {
    return Complex.rectangular(this.re() + other.re(), this.im() + other.im())
        .toPolar();
  }
}
