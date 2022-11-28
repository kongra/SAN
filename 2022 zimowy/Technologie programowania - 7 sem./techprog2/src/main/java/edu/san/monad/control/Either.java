// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.monad.control;

import java.util.Objects;
import java.util.function.Supplier;

public sealed interface Either<L, R> {

  record Left<L, R> (L value) implements Either<L, R> {
    public Left {
      Objects.requireNonNull(value);
    }
  }

  record Right<L, R> (R value) implements Either<L, R> {
    public Right {
      Objects.requireNonNull(value);
    }
  }

  default <X extends Throwable> R rightOrElseThrow(
      Supplier<? extends X> exceptionSupplier) throws X {

    if (this instanceof Right<L, R> right)
      return right.value;

    throw exceptionSupplier.get();
  }

}
