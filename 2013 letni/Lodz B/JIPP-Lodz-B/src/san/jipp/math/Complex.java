package san.jipp.math;

public abstract class Complex extends Number {

  public static Complex algebraic(double re, double im) {
    return new AlgebraicComplex(re, im);
  }

  public static Complex polar(double modulus, double argument) {
    return new PolarComplex(modulus, argument);
  }

  public abstract double re();

  public abstract double im();

  public abstract double modulus();

  public abstract double argument();

  public abstract Complex polar();

  public abstract Complex algebraic();

  public Complex add(Complex other) {
    return Complex.algebraic(this.re() + other.re(), this.im() + other.im());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(im());
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(re());
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Complex)) {
      return false;
    }
    Complex other = (Complex) obj;
    if (Double.doubleToLongBits(im()) != Double.doubleToLongBits(other.im())) {
      return false;
    }
    if (Double.doubleToLongBits(re()) != Double.doubleToLongBits(other.re())) {
      return false;
    }
    return true;
  }

  @Override
  public byte byteValue() {
    return (byte) doubleValue();
  }

  @Override
  public double doubleValue() {
    return this.re();
  }

  @Override
  public float floatValue() {
    return (float) doubleValue();
  }

  @Override
  public int intValue() {
    return (int) doubleValue();
  }

  @Override
  public long longValue() {
    return (long) doubleValue();
  }

  @Override
  public short shortValue() {
    return (short) doubleValue();
  }

}
