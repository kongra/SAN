package test.san.coll;

public final class Adder {

  public interface F<T extends Number> {
    double f(T y);
  }

  public static <T extends Number> F<T> adder(final T x) {
    return new F<T>() {
      @Override
      public double f(T y) {
        return x.doubleValue() + y.doubleValue();
      }
    };
  }

  private Adder() {
  }
}
