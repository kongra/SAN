package san.jipp.math;

public abstract class Complex {

  public static Complex rectangular(double real, double imag) {
    return new RectangularComplex(real, imag);
  }

  public static Complex polar(double modulus, double argument) {
    return new PolarComplex(modulus, argument);
  }

  public abstract double real();

  public abstract double imag();

  public abstract double modulus(); // |z|

  public abstract double argument(); // φ

  @Override
  public abstract String toString();

  public abstract Complex toPolar();

  public abstract Complex toRectangular();

  public Complex add(Complex other) {
    return Complex.rectangular(this.real() + other.real(), this.imag()
        + other.imag());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(imag());
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(real());
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
    if (Double.doubleToLongBits(imag()) != Double
        .doubleToLongBits(other.imag())) {
      return false;
    }
    if (Double.doubleToLongBits(real()) != Double
        .doubleToLongBits(other.real())) {
      return false;
    }
    return true;
  }

}
