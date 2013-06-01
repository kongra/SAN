package san.jipp.math;

public abstract class Complex extends Number {

  public static Complex rectangular(double re, double im) {
    return new RectangularComplex(re, im);
  }

  public static Complex rectangular(double value) {
    return rectangular(value, 0);
  }

  public static Complex polar(double modulus, double argument) {
    return new PolarComplex(modulus, argument);
  }

  public abstract double re();

  public abstract double im();

  public abstract double modulus();

  public abstract double argument();

  public abstract Complex toRectangular();

  public abstract Complex toPolar();

  @Override
  public abstract String toString();

  public abstract Complex add(Complex other);

  public Complex add(double value) {
    return add(Complex.rectangular(value));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(this.im());
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(this.re());
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
  public double doubleValue() {
    return re();
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

}
