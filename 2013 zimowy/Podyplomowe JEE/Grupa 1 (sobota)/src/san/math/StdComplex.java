package san.math;

class StdComplex<T extends Number> extends Complex<T> {

  private final T re;

  private final T im;

  StdComplex(T re, T im) {
    this.re = re;
    this.im = im;
  }

  @Override
  public T re() {
    return this.re;
  }

  @Override
  public T im() {
    return this.im;
  }

  @Override
  public T modulus() {
    return null; // Math.sqrt(this.re * this.re + this.im * this.im);
  }

  @Override
  public T argument() {
    return null; // Math.atan2(this.im(), this.re());
  }

  @Override
  public String toString() {
    // if (this.re() == 0) {
    // return this.im() + "j";
    // }
    // StringBuilder repr = new StringBuilder("(");
    //
    // double imAbs = Math.abs(this.im());
    // String sign = Math.signum(this.im()) < 0 ? "-" : "+";
    //
    // repr.append(this.re).append(sign).append(imAbs).append("j");
    // return repr.append(")").toString();
    return "";
  }

  @Override
  public Complex<T> add(Complex<? extends Number> other) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Complex<T> asCarthesian() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Complex<T> asPolar() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int intValue() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public long longValue() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public float floatValue() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double doubleValue() {
    // TODO Auto-generated method stub
    return 0;
  }

  
}
