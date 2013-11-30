package san.coll;

public class LinkedSeq<T> implements ISeq<T> {

  @SafeVarargs
  public static <S> ISeq<S> create(S... elements) {
    ISeq<S> result = empty();
    for (int i = elements.length - 1; i >= 0; i--) {
      result = result.cons(elements[i]);
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public static <S> ISeq<S> empty() {
    return (ISeq<S>) EMPTY;
  }

  private static final ISeq<Object> EMPTY = new ISeq<Object>() {

    @Override
    public Object first() {
      return null;
    }

    @Override
    public ISeq<Object> rest() {
      return this;
    }

    @SuppressWarnings("synthetic-access")
    @Override
    public ISeq<Object> cons(Object obj) {
      return new LinkedSeq<>(obj, this);
    }

    @Override
    public boolean isEmpty() {
      return true;
    }

    @Override
    public ISeq<Object> interpose(Object separator) {
      return this;
    }

  };

  private final T first;

  private final ISeq<T> rest;

  private LinkedSeq(T first, ISeq<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public T first() {
    return this.first;
  }

  @Override
  public ISeq<T> rest() {
    return this.rest;
  }

  @Override
  public ISeq<T> cons(T obj) {
    return new LinkedSeq<T>(obj, this);
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public ISeq<T> interpose(T separator) {
    return Utils.interpose(separator, this);
  }

  @Override
  public String toString() {
    return Utils.toString(this);
  }

}
