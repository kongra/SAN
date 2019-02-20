package san.utils;

import san.conc.Delay;

public class LazySeq<T> implements Seq<T> {

  private final T first;

  private final Delay<Seq<T>> rest;

  public LazySeq(T first, Delay<Seq<T>> rest) {
    this.first = first;
    this.rest = rest;
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
  public Seq<T> cons(T obj) {
    return new LazySeq<>(obj, new Delay<>(() -> this));
  }

  @Override
  public boolean isEmpty() {
    return false;
  }
}
