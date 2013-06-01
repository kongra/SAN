package san.jipp.math;

public abstract class Complex<T extends Num<T>> implements Num<Complex<T>> {

  public static <S extends Num<S>> Complex<S> algebraic(S re, S im) {
    return new AlgebraicComplex<S>(re, im);
  }

  public static <S extends Num<S>> Complex<S> polar(S modulus, S argument) {
    return new PolarComplex<S>(modulus, argument);
  }

  public abstract T re();

  public abstract T im();

  public abstract T modulus();

  public abstract T argument();

  public abstract Complex<T> polar();

  public abstract Complex<T> algebraic();

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = im().hashCode();
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = re().hashCode();
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
    if (!im().equals(other.im())) {
      return false;
    }
    if (!re().equals(other.re())) {
      return false;
    }
    return true;
  }
}
