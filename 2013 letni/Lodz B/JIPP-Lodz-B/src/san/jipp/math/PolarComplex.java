package san.jipp.math;

import san.jipp.util.Seq;

class PolarComplex<T extends Num<T>> extends Complex<T> {

  private final T modulus;

  private final T argument;

  PolarComplex(T modulus, T argument) {
    this.modulus = modulus;
    this.argument = argument;
  }

  @Override
  public T argument() {
    return this.argument;
  }

  @Override
  public T im() {
    return this.modulus().mul(this.argument().sin());
  }

  @Override
  public T modulus() {
    return this.modulus;
  }

  @Override
  public T re() {
    return this.modulus().mul(this.argument().cos());
  }

  @Override
  public Complex<T> algebraic() {
    return Complex.algebraic(this.re(), this.im());
  }

  @Override
  public Complex<T> polar() {
    return this;
  }

  @Override
  public String toString() {
    return modulus() + "∠" + argument();
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
