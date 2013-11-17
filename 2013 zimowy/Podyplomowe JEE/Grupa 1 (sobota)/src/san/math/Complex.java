package san.math;

public abstract class Complex<T extends Number> extends Number {

  public static <S extends Number> Complex<S> carthesian(S re, S im) {
    return new StdComplex<S>(re, im);
  }

  public static <S extends Number> Complex<S> polar(S modulus, S argument) {
    return new PolarComplex<S>(modulus, argument);
  }

  public abstract T re();

  public abstract T im();

  public abstract T modulus();

  public abstract T argument();

  @Override
  public abstract String toString();

  public abstract Complex<T> add(Complex<? extends Number> other);

  public abstract Complex<T> asCarthesian();

  public abstract Complex<T> asPolar();

}
