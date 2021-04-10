package bdia;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class DynVar<T> {

  @Contract("_ -> new")
  public static <S> @NotNull DynVar<S> initially(S initialValue) {
    return new DynVar<S>(initialValue);
  }

  @Contract(" -> new")
  public static <S> @NotNull DynVar<S> create() {
    return new DynVar<S>(null);
  }

  public T value() {
    Stack<T> stack = localStacks.get();
    return !stack.isEmpty() ? (T) stack.peek() : initialValue;
  }

  public void binding(T value, @NotNull Runnable body) {
    Stack<T> stack = localStacks.get();
    stack.push(value);
    try {
      body.run();
    }
    finally {
      localStacks.get().pop();
    }
  }

  private final ThreadLocal<Stack<T>> localStacks =
      ThreadLocal.withInitial(() -> new Stack<>());

  private final T initialValue;

  private DynVar(T initialValue) {
    this.initialValue = initialValue;
  }
}
