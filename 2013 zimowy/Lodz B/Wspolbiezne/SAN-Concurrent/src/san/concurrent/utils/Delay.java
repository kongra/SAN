package san.concurrent.utils;

import san.fn.Nullary;

public class Delay<T> {

  public static <S> Delay<S> create(Nullary<S> provider) {
    return new Delay<>(provider);
  }

  public T deref() {
    if (pending) {
      value = provider.call();
      pending = false;
    }

    return value;
  }

  private final Nullary<T> provider;

  private boolean pending;

  private T value;

  private Delay(Nullary<T> provider) {
    this.provider = provider;
    this.pending = true;
  }

}
