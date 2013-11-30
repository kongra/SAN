package san.math.numbers;

public abstract class Num implements WithAdd {

  public abstract boolean isZero();

  @Override
  public abstract int hashCode();

  @Override
  public abstract boolean equals(Object obj);

  @Override
  public abstract String toString();

}
