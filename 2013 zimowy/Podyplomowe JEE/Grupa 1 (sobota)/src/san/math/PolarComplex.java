package san.math;

class PolarComplex<T extends Number> extends Complex<T> {

  private final T modulus;

  private final T argument;

  PolarComplex(T modulus, T argument) {
    this.modulus = modulus;
    this.argument = argument;
  }

  @Override
  public T re() {
    return null; // this.modulus().doubleValue() * Math.cos(this.argument());
  }

  @Override
  public T im() {
    return null; // this.modulus() * Math.sin(this.argument());
  }

  @Override
  public T modulus() {
    return this.modulus;
  }

  @Override
  public T argument() {
    return this.argument;
  }

  @Override
  public String toString() {
    return this.modulus() + "∠" + this.argument();
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
