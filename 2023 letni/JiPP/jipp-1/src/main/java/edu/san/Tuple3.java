// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.Objects;

public record Tuple3<T, S, R>(T fst, S snd, R thrd) {

  public static <T, S, R> Tuple3<T, S, R> of(T fst, S snd, R thrd) {
    return new Tuple3<>(fst, snd, thrd);
  }

  public Tuple3 {
    Objects.requireNonNull(fst);
    Objects.requireNonNull(snd);
    Objects.requireNonNull(thrd);
  }

}
