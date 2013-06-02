package san.util;

import java.util.Stack;

public class DynVar<T> {

  public static <S> DynVar<S> initially(S initialValue) {
    return new DynVar<S>(initialValue);
  }

  public static <S> DynVar<S> create() {
    return new DynVar<S>(null);
  }

  @SuppressWarnings("unchecked")
  public T value() {
    Stack<Object> stack = localStacks.get();
    return !stack.isEmpty() ? (T) stack.peek() : initialValue;
  }

  public void binding(T value, Runnable body) {
    Stack<Object> stack = localStacks.get();
    stack.push(value);
    try {
      body.run();
    }
    finally {
      localStacks.get().pop();
    }
  }

  private final ThreadLocal<Stack<Object>> localStacks =
      new ThreadLocal<Stack<Object>>() {
        @Override
        protected Stack<Object> initialValue() {
          return new Stack<Object>();
        }
      };

  private final T initialValue;

  private DynVar(T initialValue) {
    this.initialValue = initialValue;
  }
}
