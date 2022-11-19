package edu.san.conc;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * A realization of lazy evaluation of expressions in Java
 * 
 * @author kongra
 */
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
    if (isMaterialized()) {
      return value;
    }

    // NACZELNA ZASADA SYNCHRONIZACJI:
    // W sekcjach krytycznych NIE WOLNO wywoływać CUDZEGO KODU!!!
    this.value = expression.get();
    this.expression = null;

    return value;
  }

//  public T value() {
//    if (isMaterialized()) {
//      return this.value;
//    }
//
//    // NACZELNA ZASADA SYNCHRONIZACJI:
//    // W sekcjach krytycznych NIE WOLNO wywoływać CUDZEGO KODU!!!
//    final var expressionValue = expression.get();
//    synchronized (this) {
//      this.value = expressionValue;
//      this.expression = null;
//
//      return expressionValue;
//    }
//  }

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
