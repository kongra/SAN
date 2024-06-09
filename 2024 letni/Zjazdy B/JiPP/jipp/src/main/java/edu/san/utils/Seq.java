// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.utils;

import java.util.Optional;

public interface Seq<E> {

  E first();

  Seq<E> rest();

  Seq<E> cons(E x);

  boolean isEmpty();

  @FunctionalInterface
  interface SeqConsumer<E> {

    void accept(E element, boolean isLast);

  }

  default void forEach(SeqConsumer<E> body) {
    var s = this;
    while (!s.isEmpty()) {
      final var element = s.first();
      s = s.rest();
      body.accept(element, s.isEmpty());
    }
  }

  @SuppressWarnings("unchecked")
  static <E> Seq<E> empty() {
    return Nil.INSTANCE;
  }

  @SafeVarargs
  static <E> Seq<E> of(E... elements) {
    final var n = elements.length;
    Seq<E> seq = Seq.empty();
    for (var i = n - 1; i >= 0; i--) {
      seq = seq.cons(elements[i]);
    }
    return seq;
  }

  static <E> String asString(Seq<E> seq) {
    final var buff = new StringBuilder("(");
    seq.forEach((e, isLast) -> {
      buff.append(e);
      if (!isLast) {
        buff.append(",");
      }
    });
    return buff.append(")").toString();
  }

  public static <T> Optional<T> nth(Seq<T> seq, int n) {
    while (true) {
      if (seq.isEmpty())
        return Optional.empty();

      if (n == 0)
        return Optional.of(seq.first());

      n--;
      seq = seq.rest();
    }
  }
}
