// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.monad.control;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

public sealed interface Either<L, R> {

  record Left<L, R> (L left) implements Either<L, R> {

    @Override
    public R right() {
      throw new NoSuchElementException("No right value present");
    }

  }

  record Right<L, R> (R right) implements Either<L, R> {

    @Override
    public L left() {
      throw new NoSuchElementException("No left value present");
    }

  }

  L left();

  R right();

  default <X extends Throwable> R rightOrElseThrow(
      Supplier<? extends X> exceptionSupplier) throws X {

    if (this instanceof Right<L, R> right)
      return right.right;

    throw exceptionSupplier.get();
  }

}
