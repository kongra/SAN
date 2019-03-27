package san.lodz.algo.utils;

public class LinkedSeq<T> implements Seq<T> {

  public static <T> Seq<T> make(T... elems) {
    Seq<T> result = Nil.get();
    for (int i = elems.length - 1; i >= 0; i--) {
      result = result.cons(elems[i]);
    }
    return result;
  }

  private final T first;

  private final Seq<T> rest;

  LinkedSeq(T first, Seq<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public T first() {
    return this.first;
  }

  @Override
  public Seq<T> rest() {
    return this.rest;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public Seq<T> cons(T e) {
    return new LinkedSeq<>(e, this);
  }
}
