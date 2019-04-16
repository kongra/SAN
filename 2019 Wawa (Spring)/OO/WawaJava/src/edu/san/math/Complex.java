package edu.san.math;

public interface Complex {

  static Complex polar(double modulus, double argument) {
    return new PolarComplex(modulus, argument);
  }

  static Complex planar(double re, double im) {
    return new PlanarComplex(re, im);
  }

  double re();

  double im();

  double modulus();

  double argument();

  String asString();

  Complex asPolar();

  Complex asPlanar();

  default Complex add(Complex other) {
    return Complex.planar(
        this.re() + other.re(),
        this.im() + other.im());
  }

}
