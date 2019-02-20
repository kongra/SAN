package san.math;

public abstract class Complex {

  public static Complex planar(double re, double im) {
    return new PlanarComplex(re, im);
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

  public abstract Complex asPlanar();

  public abstract Complex asPolar();

}
