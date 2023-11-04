// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.math;

record RectangularComplex(double re, double im) implements Complex {

  @Override
  public double mod() {
    return Math.sqrt(re() * re() + im() * im());
  }

  @Override
  public double arg() {
    return Math.atan2(im(), re());
  }

  @Override
  public Complex asRectangular() {
    return this;
  }

  @Override
  public Complex asPolar() {
    return Complex.polar(mod(), arg());
  }
  
  
}
