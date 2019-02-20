package san.utils;

public class LinkedSeq<T> implements Seq<T> {

  public static <S> Seq<S> of(S... elements) {
    Seq<S> result = null;
    for (int i = elements.length - 1; i >= 0; i--) {
      result = new LinkedSeq<>(elements[i], result);
    }
    return result;
  }

  private final T first;

  private final Seq<T> rest;

  public LinkedSeq(T first, Seq<T> rest) {
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
  public Seq<T> cons(T x) {
    return new LinkedSeq<>(x, this);
  }

}
