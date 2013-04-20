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
  public double im() {
    return this.modulus() * Math.sin(this.argument());
  }

  @Override
  public double modulus() {
    return this.modulus;
  }

  @Override
  public double re() {
    return this.modulus() * Math.cos(this.argument());
  }

  @Override
  public Complex algebraic() {
    return Complex.algebraic(this.re(), this.im());
  }

  @Override
  public Complex polar() {
    return this;
  }

  @Override
  public String toString() {
    return modulus() + "∠" + argument();
  }

}
