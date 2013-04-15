package san.jipp.math;

public class Complex {

  public final double re;

  public final double im;

  public Complex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  public Complex(double value) {
    this(value, 0);
  }

  public String repr() {
    if (re != 0) {
      if (im != 0) {
        // re + im j
        return new StringBuilder().append("(").append(re).append("+")
            .append(im).append("j").append(")").toString();
      }
      // re
      return new StringBuilder().append("(").append(re).append("+0.0j)")
          .toString();
    }
    if (im != 0) {
      return String.valueOf(im) + "j";
    }
    return "0.0j";
  }

  public Complex add(Complex other) {
    return new Complex(this.re + other.re, this.im + other.im);
  }

  public Complex add(double value) {
    return add(new Complex(value));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(im);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(re);
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
    if (Double.doubleToLongBits(im) != Double.doubleToLongBits(other.im)) {
      return false;
    }
    if (Double.doubleToLongBits(re) != Double.doubleToLongBits(other.re)) {
      return false;
    }
    return true;
  }
  
  

}
