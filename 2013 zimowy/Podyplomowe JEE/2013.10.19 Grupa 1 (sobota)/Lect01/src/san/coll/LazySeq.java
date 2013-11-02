package san.coll;

import san.coll.fn.NoArg;
import san.coll.fn.Promise;

public class LazySeq implements ISeq {

  public static ISeq create(Object first, NoArg producer) {
    return new LazySeq(first, Promise.create(producer));
  }

  private final Object first;

  private final Promise promise;

  private LazySeq(Object first, Promise promise) {
    this.first = first;
    this.promise = promise;
  }

  @Override
  public Object first() {
    return this.first;
  }

  @Override
  public ISeq rest() {
    return (ISeq) promise.call();
  }

  @Override
  public ISeq cons(Object obj) {
    return create(obj, new NoArg() {
      @Override
      public Object call() {
        return LazySeq.this;
      }
    });
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

}
