package san.math;

public class Complex {

  private final double re;

  private final double im;

  public Complex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Complex complex = (Complex) o;

    if (Double.compare(complex.re, re) != 0) return false;
    return Double.compare(complex.im, im) == 0;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(re);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(im);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
