// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.utils;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import telsos.Delay;

final class LazySeq<E> implements Seq<E> {

  private final E first;

  private final Delay<Seq<E>> rest;

  // (1, 2, 3, 4)
  // [1] -> Delay([2] -> Delay([3] -> Delay([4] -> Delay(Nil))))
  // N = (0, 1, 2, 3, 4, ...)
  // N = [0] -> Delay([1] -> ...)

  private LazySeq(E first, Supplier<Seq<E>> rest) {
    this.first = first;
    this.rest  = Delay.of(rest);
  }

  @Override
  public E first() {
    return this.first;
  }

  @Override
  public Seq<E> rest() {
    return this.rest.deref();
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public Seq<E> cons(E e) {
    return new LazySeq<>(e, () -> this);
  }

  public static <T> LazySeq<T> iterate(UnaryOperator<T> f, T start) {
    return new LazySeq<>(start, () -> iterate(f, f.apply(start)));
  }

  // iterate(f, start) => (start, iterate(f, f(start))
  // iterate(f, start) => (start, f(start), f(f(start)), f(f(f(start))), ...)

  // Przykład: liczby naturalne
  // inc = n -> n + 1
  // N = iterate(inc, 0)   => (0, inc(0), inc(inc(0)), inc(inc(inc(0))), ...) => (0, 1, 2, 3, ...)

  public static <T, S> Seq<S> fmap(Function<T, S> f, Seq<T> s) {
    if (s.isEmpty())
      return Seq.empty();

    return new LazySeq<>(f.apply(s.first()), () -> fmap(f, s.rest()));
  }

  // f : A, T -> A, gdzie T - typ elementów, A - typ akumulatora

  // reduce(+, 0, (1,  2,  3,  4))    =>
  // reduce(+, 0 + 1, (2,  3,  4))    =>
  // reduce(+, 0 + 1 + 2, (3,  4))    =>
  // reduce(+, 0 + 1 + 2 + 3, (4))    =>
  // reduce(+, 0 + 1 + 2 + 3 + 4, ()) => 0 + 1 + 2 + 3 + 4

  public static <T, A> A reduce(BiFunction<A, T, A> f, A acc, Seq<T> s) {
    if (s.isEmpty())
      return acc;

    return reduce(f, f.apply(acc, s.first()), s.rest());
  }

  public static <T> Seq<T> take(int n, Seq<T> s) {
    if(n == 0)
      return Seq.empty();

    if (s.isEmpty())
      return Seq.empty();

    return new LazySeq<>(s.first(), () -> take(n - 1, s.rest()));
  }

  public static <T> Seq<T> filter(Predicate<T> pred, Seq<T> s) {
    if (s.isEmpty())
      return Seq.empty();

    T e = s.first();
    if (pred.test(e))
      return new LazySeq<>(e, () -> filter(pred, s.rest()));

    return filter(pred, s.rest());
  }
}
