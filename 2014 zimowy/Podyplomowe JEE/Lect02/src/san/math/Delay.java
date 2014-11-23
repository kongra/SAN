package san.math;

public final class Delay<T> {

  private T value;

  private Nfn<T> expression;

  public Delay(Nfn<T> expression) {
    this.expression = expression;
  }

  public T deref() {
    if (expression != null) {
      value = expression.call();
      expression = null;
    }
    return value;
  }

  @Override
  public String toString() {
    if (expression != null) {
      return "Delay :pending";
    }
    return "Delay " + value;
  }

}
