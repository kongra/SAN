package san.jipp.math;

public class Complex {

  public final double re;

  public final double im;

  public Complex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  public Complex(double other) {
    this(other, 0);
  }

  public double modulus() {
    return Math.sqrt(this.re * this.re + this.im * this.im);
  }

  public double argument() {
    if (this.re > 0) {
      return Math.atan(this.im / this.re);
    }
    else if (this.re < 0 && this.im >= 0) {
      return Math.atan(this.im / this.re) + Math.PI;
    }
    else if (this.re < 0 && this.im < 0) {
      return Math.atan(this.im / this.re) - Math.PI;
    }
    else if (this.re == 0 && this.im > 0) {
      return Math.PI / 2;
    }
    else if (this.re == 0 && this.im < 0) {
      return -Math.PI / 2;
    }
    return Double.NaN;
  }

  public Complex add(Complex other) {
    return new Complex(this.re + other.re, this.im + other.im);
  }

  public Complex add(double other) {
    return add(new Complex(other));
  }

  public void print() {
    StringBuilder buf = new StringBuilder("C(");

    if (this.re != 0.0) {
      buf.append(this.re);
      if (this.im != 0.0) {
        buf.append(" + ").append(this.im).append("i");
      }
    }
    else if (this.im != 0.0) {
      buf.append(this.im).append("i");
    }

    buf.append(")");
    System.out.println(buf.toString());
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
    if (getClass() != obj.getClass()) {
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

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Complex [im=").append(im).append(", re=").append(re)
        .append("]");
    return builder.toString();
  }

}
