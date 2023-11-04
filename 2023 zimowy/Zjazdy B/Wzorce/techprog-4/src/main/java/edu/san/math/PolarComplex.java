// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.math;

record PolarComplex(double mod, double arg) implements Complex {

  @Override
  public double re() {
    return mod * Math.cos(arg);
  }

  @Override
  public double im() {
    return mod * Math.sin(arg);
  }

  @Override
  public Complex asRectangular() {
    return Complex.rectangular(re(), im());
  }

  @Override
  public Complex asPolar() {
    return this;
  }

}