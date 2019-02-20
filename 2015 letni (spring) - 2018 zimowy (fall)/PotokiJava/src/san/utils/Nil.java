package san.utils;

public class Nil<T> implements Seq<T> {

  public static <S> Seq<S> nil() {
    return new Nil();
  }

  @Override
  public T first() {
    return null;
  }

  @Override
  public Seq<T> rest() {
    return this;
  }

  @Override
  public Seq<T> cons(T obj) {
    return new LinkedSeq<>(obj, this);
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

}
