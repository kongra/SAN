package san.coll;

import san.fn.Delay;
import san.fn.Nullary;

public class LazySeq implements ISeq {

  public static ISeq create(Object first, Nullary restProvider) {
    return new LazySeq(first, restProvider);
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
  public ISeq cons(Object obj) {
    return new LinkedSeq(obj, this);
  }

  @Override
  public String toString() {
    return Utils.toString(this);
  }

  private final Object first;

  private final Delay rest;

  private LazySeq(Object first, Nullary restProvider) {
    this.first = first;
    this.rest = Delay.of(restProvider);
  }
}
