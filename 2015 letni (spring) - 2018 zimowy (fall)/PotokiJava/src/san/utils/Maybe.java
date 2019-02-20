package san.utils;

public final class Maybe<T> {

  public static <T> Maybe<T> nothing() {
    return new Maybe<>(null, true);
  }

  public static <T> Maybe<T> just(T value) {
    return new Maybe<>(value, false);
  }

  public boolean isNothing() {
    return this.nothing;
  }

  public T value() {
    if (isNothing()) throw new IllegalArgumentException();
    return this.value;
  }

  private final boolean nothing;

  private final T value;

  private Maybe(T value, boolean nothing) {
    this.value = value;
    this.nothing = nothing;
  }

}
