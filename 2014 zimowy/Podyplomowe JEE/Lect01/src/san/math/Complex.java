package san.math;

public abstract class Complex {

  public static Complex of(double re, double im) {
    return new PlanarComplex(re, im);
  }

  public static Complex polar(double modulus, double angle) {
    return new PolarComplex(modulus, angle);
  }

  public abstract double re();

  public abstract double im();

  public abstract double modulus();

  public abstract double angle();

  public abstract Complex add(Complex other);

  public abstract Complex asPlanar();

  public abstract Complex asPolar();

  @Override
  public final int hashCode() {
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
  public final boolean equals(Object obj) {
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

}
