package edu.san.utils;

import java.util.Objects;
import java.util.function.Supplier;

public final class Delay<T> {

  public static <T> Delay<T> of(Supplier<T> expression) {
    return new Delay<>(expression);
  }

  private Supplier<T> expression;

  private T value;

  private Delay(Supplier<T> expression) {
    Objects.requireNonNull(expression);
    this.expression = expression;
  }

  public synchronized boolean isMaterialized() {
    return expression == null;
  }

  public synchronized T value() {
    if (isMaterialized())
      return value;

    // NACZELNA ZASADA SYNCHRONIZACJI:
    // W sekcjach krytycznych NIE WOLNO wywoływać CUDZEGO KODU!!!
    this.value = expression.get();
    this.expression = null;

    return value;
  }

  @Override
  public int hashCode() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object obj) {
    throw new UnsupportedOperationException();
  }

//  public Object deref() {
//    if (fn != null) {
//      synchronized (this) {
//        // double check
//        if (fn != null) {
//          try {
//            val = fn.invoke();
//          } catch (Throwable t) {
//            exception = t;
//          }
//          fn = null;
//        }
//      }
//    }
//    if (exception != null)
//      throw Util.sneakyThrow(exception);
//    return val;
//  }
}