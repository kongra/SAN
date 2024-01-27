// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.Stack;

public final class DynVar<T> implements AutoCloseable {

  public static <T> DynVar<T> initially(T initialValue) {
    return new DynVar<>(initialValue);
  }

  public static <T> DynVar<T> create() {
    return new DynVar<>(null);
  }

  public T value() {
    final var stack = localStacks.get();
    return !stack.isEmpty() ? stack.peek() : initialValue;
  }

  public void binding(T value, Runnable body) {
    final var stack = localStacks.get();
    stack.push(value);
    try {
      body.run();
    } finally {
      localStacks.get().pop();
    }
  }

  @Override
  public void close() throws Exception {
    localStacks.remove();
  }

  private final ThreadLocal<Stack<T>> localStacks = ThreadLocal
      .withInitial(Stack::new);

  private final T initialValue;

  private DynVar(T initialValue) {
    this.initialValue = initialValue;
  }
}
