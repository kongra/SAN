// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.monad.control;

import java.util.Objects;
import java.util.function.Supplier;

public sealed interface Either<L, R> {

  static <L, R> Either<L, R> left(L value) {
    return new Left<>(value);
  }

  static <L, R> Either<L, R> right(R value) {
    return new Right<>(value);
  }

  record Left<L, R>(L value) implements Either<L, R> {
    public Left {
      Objects.requireNonNull(value);
    }
  }

  record Right<L, R>(R value) implements Either<L, R> {
    public Right {
      Objects.requireNonNull(value);
    }
  }

  default <X extends Throwable> R rightOrElseThrow(
      Supplier<? extends X> exceptionSupplier) throws X {
    return switch (this) {
      case Right<L, R> right -> right.value;
      default -> throw exceptionSupplier.get();
    };
  }

}
