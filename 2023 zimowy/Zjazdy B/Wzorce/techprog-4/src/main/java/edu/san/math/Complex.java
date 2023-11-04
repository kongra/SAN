// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.math;

public sealed interface Complex permits RectangularComplex, PolarComplex {

  static Complex rectangular(double re, double im) {
    return new RectangularComplex(re, im);
  }
  
  static Complex polar(double mod, double arg) {
    return new PolarComplex(mod, arg);
  }
  
  double re();
  
  double im();
  
  double mod();
  
  double arg();
  
  Complex asRectangular();
  
  Complex asPolar();
  
}
