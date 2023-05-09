// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.Objects;

public final class Tuple2<T, S> {
  // T, S - zmienne typowe (ang. type-variables)

  public static <T, S> Tuple2<T, S> of(T fst, S snd) {
    return new Tuple2<>(fst, snd);
  }

  public final T fst;

  public final S snd;

  private Tuple2(T fst, S snd) {
    this.fst = Objects.requireNonNull(fst);
    this.snd = Objects.requireNonNull(snd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fst, snd);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    @SuppressWarnings("unchecked")
    final var other = (Tuple2<T, S>) obj;
    return Objects.equals(fst, other.fst) && Objects.equals(snd, other.snd);
  }

  @Override
  public String toString() {
    return "(" + fst + ", " + snd + ")";
  }

}
