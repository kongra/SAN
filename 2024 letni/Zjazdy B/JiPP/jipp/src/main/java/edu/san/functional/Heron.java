// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.functional;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public record Heron<T>(
    BinaryOperator<T> add,
    BinaryOperator<T> subtract,
    BinaryOperator<T> multiply,
    BinaryOperator<T> divide,
    UnaryOperator<T> negate,
    BiPredicate<T, T> lowerThan,
    T zero,
    T one,
    T two,
    T epsilon) {

  public Heron {
    Objects.requireNonNull(add);
    Objects.requireNonNull(subtract);
    Objects.requireNonNull(multiply);
    Objects.requireNonNull(divide);
    Objects.requireNonNull(negate);
    Objects.requireNonNull(lowerThan);
    Objects.requireNonNull(zero);
    Objects.requireNonNull(one);
    Objects.requireNonNull(two);
    Objects.requireNonNull(epsilon);
  }

  public T sqrt(T x) {
    return sqrt(one, x);
  }

  private T sqrt(T g, T x) {
    return isGoodEnough(g, x) ? g : sqrt(improve(g, x), x);
  }

  private T improve(T g, T x) {
    return avg(g, divide.apply(x, g));
  }

  private T avg(T x, T y) {
    return divide.apply(add.apply(x, y), two);
  }

  private boolean isGoodEnough(T g, T x) {
    return lowerThan.test(abs(subtract.apply(square(g), x)),
        epsilon);
  }

  private T abs(T d) {
    return lowerThan.test(d, zero) ? negate.apply(d) : d;
  }

  private T square(T g) {
    return multiply.apply(g, g);
  }

}
