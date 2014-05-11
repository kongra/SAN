package san.algo;

import san.fn.Delay;
import san.fn.Nullary;

public class LazySeq implements ISeq {

  public static ISeq of(Object first, Delay rest) {
    return new LazySeq(first, rest);
  }

  private final Object first;

  private final Nullary rest;

  private LazySeq(Object first, Nullary rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public Object first() {
    return first;
  }

  @Override
  public ISeq rest() {
    return (ISeq) rest.call();
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public ISeq cons(Object x) {
    return new LazySeq(x, Delay.of(new Nullary() {
      @Override
      public Object call() {
        return LazySeq.this;
      }
    }));
  }

}
