// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.utils;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import telsos.Delay;

public class LazySeq<T> implements Seq<T> {

  private final T first;

  private final Delay<Seq<T>> rest;

  public LazySeq(T first, Supplier<Seq<T>> rest) {
    this.first = first;
    this.rest  = Delay.of(rest);
  }

  @Override
  public T first() {
    return this.first;
  }

  @Override
  public Seq<T> rest() {
    return this.rest.deref();
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public Seq<T> cons(T e) {
    return new LazySeq<>(e, () -> this);
  }

  public static <T> LazySeq<T> iterate(UnaryOperator<T> f, T start) {
    return new LazySeq<>(start, () -> iterate(f, f.apply(start)));
  }

  public static <T, S> Seq<S> fmap(Function<T, S> f, Seq<T> s) {
    if (s.isEmpty())
      return Seq.empty();

    return new LazySeq<>(f.apply(s.first()), () -> fmap(f, s.rest()));
  }

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
