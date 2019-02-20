package nonstattionary.math;

public class Cplx {

  double re;

  double im;

  static Cplx add(Cplx c1, Cplx c2) {
    Cplx result = new Cplx();
    result.re = c1.re + c2.re;
    result.im = c1.im + c2.im;
    return result;
  }

}
