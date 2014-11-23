package san.coll;

import san.math.Delay;
import san.math.Nfn;

public class LazySeq<T> implements ISeq<T> {

  private final T first;

  private final Delay<ISeq<T>> rest;

  LazySeq(T first, Delay<ISeq<T>> rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public T first() {
    return first;
  }

  @Override
  public ISeq<T> rest() {
    return rest.deref();
  }

  @Override
  public ISeq<T> cons(T first) {
    Nfn<ISeq<T>> expr = new Nfn<ISeq<T>>() {
      @Override
      public ISeq<T> call() {
        return LazySeq.this;
      }
    };
    Delay<ISeq<T>> delay = new Delay<ISeq<T>>(expr);
    return new LazySeq<T>(first, delay);
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public String toString() {
    return Seqs.toString(this);
  }
}
