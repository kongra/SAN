package san.math;

public abstract class Complex {
  
  public static Complex carthesian(double re, double im) {
    return new StdComplex(re, im);
  }

  public static Complex carthesian(double re) {
    return carthesian(re, 0);
  }
  
  public static Complex polar(double modulus, double argument) {
    return new PolarComplex(modulus, argument);
  }
  
  public abstract double re();

  public abstract double im();

  public abstract double modulus();

  public abstract double argument();

  @Override
  public abstract String toString();

  public abstract Complex add(Complex other);
  
  public abstract Complex asCarthesian();
  
  public abstract Complex asPolar();

}
