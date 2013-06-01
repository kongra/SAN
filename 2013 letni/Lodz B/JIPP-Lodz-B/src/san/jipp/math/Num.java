package san.jipp.math;

import san.jipp.util.Seq;

public interface Num<T extends Num<T>> {

  T add(T other);

  T sub(T other);

  T mul(T other);

  T div(T other);

  boolean gt(T other);

  public T sin();

  public T cos();

  public Seq<T> squareRoots();
  
  boolean isZero();
  
  boolean isPositive();
  
  boolean isNegative();
  
  T abs();

}
