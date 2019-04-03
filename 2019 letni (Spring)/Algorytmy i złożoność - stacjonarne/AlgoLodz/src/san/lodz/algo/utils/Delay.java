package san.lodz.algo.utils;

import san.lodz.math.Nullary;

public final class Delay<T> implements Nullary<T> {

  public static <T> Delay<T> delay(Nullary<T> expr) {
    return new Delay<>(expr);
  }

  private Nullary<T> expr;

  private T value;

  private Delay(Nullary<T> expr) {
    this.expr = expr;
  }

  @Override
  public T call() {
    if(!isPending()) {
      return this.value;
    }
    this.value = expr.call();
    this.expr  = null;
    return this.value;
  }

  public boolean isPending() {
    return this.expr != null;
  }

  @Override
  public String toString() {
    return new StringBuilder()
        .append("delay{")
        .append(":value ")
        .append(this.value)
        .append(" :pending ")
        .append(this.isPending())
        .append("}").toString();
  }
}
