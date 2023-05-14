// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.util;

import java.util.Objects;

public final class Tuple2<F, S> {
  // F oraz S - zmienne typowe (ang. type-variables)

  public static <F, S> Tuple2<F, S> of(F fst, S snd) {
    return new Tuple2<>(fst, snd);
  }

  public final F fst;

  public final S snd;

  private Tuple2(F fst, S snd) {
    this.fst = Objects.requireNonNull(fst);
    this.snd = Objects.requireNonNull(snd);
  }

  @Override
  public int hashCode() {
    final var prime = 31;
    var result = 1;
    result = prime * result + fst.hashCode();
    result = prime * result + snd.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj instanceof final Tuple2 other) {
      return fst.equals(other.fst) && snd.equals(other.snd);
    }
    return false;
  }

  @Override
  public String toString() {
    return "(" + fst + "," + snd + ")";
  }

}
