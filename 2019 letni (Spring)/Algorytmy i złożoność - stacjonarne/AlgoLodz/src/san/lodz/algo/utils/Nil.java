package san.lodz.algo.utils;

public class Nil<T> implements Seq<T> {

  private static final Nil theSpecialOne = new Nil();

  public static <T> Nil<T> get() {
    return theSpecialOne;
  }

  private Nil() {

  }

  @Override
  public T first() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Seq<T> rest() {
    return theSpecialOne;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }
}
