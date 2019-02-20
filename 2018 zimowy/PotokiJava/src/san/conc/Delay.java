package san.conc;

import java.util.Objects;

public class Delay<T> implements Nullary<T> {

  private Nullary<T> f;

  private T value;

  public Delay(Nullary<T> f) {
    this.f = Objects.requireNonNull(f);
  }

  @Override
  public T call() {
    Nullary<T> f;
    synchronized (this) {
      f = this.f;
      if (f == null) {
        return this.value;
      }
    }

    T value = f.call();
    synchronized (this) {
      this.value = value;
      this.f = null;
    }

    return value;
  }
}
