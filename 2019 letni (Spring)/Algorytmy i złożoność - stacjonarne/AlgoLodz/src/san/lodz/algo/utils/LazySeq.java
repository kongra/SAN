package san.lodz.algo.utils;

import san.lodz.math.Nullary;
import san.lodz.math.Unary;

import static san.lodz.algo.utils.Delay.delay;

public class LazySeq<T> implements Seq<T> {

  private final T first;

  private final Delay<Seq<T>> rest;

  public LazySeq(T first, Nullary<Seq<T>> rest) {
    this.first = first;
    this.rest  = delay(rest);
  }

  @Override
  public T first() {
    return this.first;
  }

  @Override
  public Seq<T> rest() {
    return this.rest.call();
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public Seq<T> cons(T e) {
    return new LazySeq<>(e, delay(() -> this));
  }

  public static <T> LazySeq<T> iterate(Unary<T, T> f, T start) {
    return new LazySeq<>(start, () -> iterate(f, f.call(start)));
  }

  public static <T, S> Seq<S> fmap(Unary<T, S> f, Seq<T> s) {
    if (s.isEmpty())
      return Nil.get();

    return new LazySeq<>(f.call(s.first()), () -> fmap(f, s.rest()));
  }

  public static <T> Seq<T> take(int n, Seq<T> s) {
    if(n == 0)
      return Nil.get();

    if (s.isEmpty())
      return s;

    return new LazySeq<>(s.first(), () -> take(n - 1, s.rest()));
  }
}
