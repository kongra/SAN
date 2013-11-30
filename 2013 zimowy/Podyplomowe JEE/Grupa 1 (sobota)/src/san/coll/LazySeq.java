package san.coll;

import san.coll.fn.NoArg;
import san.coll.fn.Delay;

public class LazySeq<T> implements ISeq<T> {

  @SuppressWarnings("unchecked")
  public static <S> ISeq<S> create(S first, NoArg<ISeq<S>> producer) {
    return new LazySeq<S>(first, Delay.create(producer));
  }

  private final T first;

  private final Delay<ISeq<T>> promise;

  private LazySeq(T first, Delay<ISeq<T>> promise) {
    this.first = first;
    this.promise = promise;
  }

  @Override
  public T first() {
    return this.first;
  }

  @Override
  public ISeq<T> rest() {
    return promise.call();
  }

  @Override
  public ISeq<T> cons(T obj) {
    return create(obj, new NoArg<ISeq<T>>() {
      @Override
      public ISeq<T> call() {
        return LazySeq.this;
      }
    });
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public String toString() {
    return Utils.toString(this);
  }

  @Override
  public ISeq<T> interpose(T separator) {
    return Utils.interpose(separator, this);
  }

}
