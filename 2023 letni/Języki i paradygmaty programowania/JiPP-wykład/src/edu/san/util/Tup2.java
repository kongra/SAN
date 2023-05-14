// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.util;

import java.util.Objects;

public record Tup2<F, S>(F fst, S snd) {

  public static <F, S> Tup2<F, S> of(F fst, S snd) {
    return new Tup2<>(fst, snd);
  }

  public Tup2 {
    Objects.requireNonNull(fst);
    Objects.requireNonNull(snd);
  }

  @Override
  public String toString() {
    return "(" + fst + "," + snd + ")";
  }
}
