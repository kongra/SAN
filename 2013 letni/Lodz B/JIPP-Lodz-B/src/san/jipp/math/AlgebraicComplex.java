package san.jipp.math;

import san.jipp.util.Seq;

class AlgebraicComplex<T extends Num<T>> extends Complex<T> {

  private final T re;

  private final T im;

  AlgebraicComplex(T re, T im) {
    this.re = re;
    this.im = im;
  }

  @Override
  public Complex<T> add(Complex<T> other) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Complex<T> div(Complex<T> other) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean gt(Complex<T> other) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Complex<T> mul(Complex<T> other) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Complex<T> sub(Complex<T> other) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public T argument() {
    // if (this.re().gt(0) {
    // return Math.atan(this.im().div(this.re()));
    // }
    // else if (this.re() < 0 && this.im() >= 0) {
    // return Math.atan(this.im().div(this.re())) + Math.PI;
    // }
    // else if (this.re() < 0 && this.im() < 0) {
    // return Math.atan(this.im() / this.re()) - Math.PI;
    // }
    // else if (this.re() == 0 && this.im() > 0) {
    // return Math.PI / 2;
    // }
    // else if (this.re() == 0 && this.im() < 0) {
    // return -Math.PI / 2;
    // }
    // return Double.NaN;

    return null;
  }

  @Override
  public T im() {
    return this.im;
  }

  @Override
  public T modulus() {
    T x = this.re().mul(this.re()).add(this.im().mul(this.im()));
    return x.squareRoots().first();
  }

  @Override
  public T re() {
    return this.re;
  }

  @Override
  public Complex<T> algebraic() {
    return this;
  }

  @Override
  public Complex<T> polar() {
    return Complex.polar(this.modulus(), this.argument());
  }

  @Override
  public Complex<T> cos() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Complex<T> sin() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Seq<Complex<T>> squareRoots() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String toString() {
    if (!this.re().isZero()) {
      if (!this.im().isZero()) {
        // (real +- imagj)
        StringBuilder buf = new StringBuilder("(");
        buf.append(this.re());
        buf.append(this.im().isNegative() ? "-" : "+");
        buf.append(this.im().abs()).append("j");
        return buf.append(")").toString();
      }
      // (real + 0j)
      return new StringBuilder("(").append(this.re()).append("+0j)").toString();
    }
    if (!this.im().isZero()) {
      return new StringBuilder(this.im().isNegative() ? "-" : "").append(
        this.im().abs()).append("j").toString();
    }
    return "0j";
  }

  @Override
  public boolean isNegative() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isPositive() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isZero() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Complex<T> abs() {
    // TODO Auto-generated method stub
    return null;
  }

}
