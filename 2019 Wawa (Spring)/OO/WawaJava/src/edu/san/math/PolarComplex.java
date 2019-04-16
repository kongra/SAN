package edu.san.math;

public class PolarComplex implements Complex {

  private final double modulus;

  private final double argument;

  PolarComplex(double modulus,
                      double argument) {
    this.modulus  = modulus;
    this.argument = argument;
  }

  @Override
  public double re() {
    return modulus() * Math.cos(argument());
  }

  @Override
  public double im() {
    return modulus() * Math.sin(argument());
  }

  @Override
  public double modulus() {
    return modulus;
  }

  @Override
  public double argument() {
    return argument;
  }

  @Override
  public String asString() {
    return modulus() + "∠" + argument();
  }

  @Override
  public Complex asPolar() {
    return this;
  }

  @Override
  public Complex asPlanar() {
    return Complex.planar(re(), im());
  }

  @Override
  public Complex add(Complex other) {
    return Complex.super.add(other).asPolar();
  }
}
