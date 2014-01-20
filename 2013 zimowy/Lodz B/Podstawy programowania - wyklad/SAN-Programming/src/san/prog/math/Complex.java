package san.prog.math;

public class Complex {

  private final double re;

  private final double im;

  public Complex(double re, double im) {
    this.re = re;
    this.im = im;
  }

  public Complex add(Complex other) {
    return new Complex(this.re + other.re, this.im + other.im);
  }
  
  public double re() {
    return this.re;
  }
  
  public double im() {
    return this.im;
  }

}
