package san.javafp;

public class HigherOrderFunctions {

  @FunctionalInterface
  public interface IntUnary { // type IntUnary = int -> int

    int eval(int i);

  }

  @FunctionalInterface
  public interface IntBinary { // type IntBinary = int -> int -> int

    int eval(int i, int j);

  }

  public static int sum(final IntUnary term, final int a, final IntUnary next,
      final int b) {
    if (a > b)
      return 0;

    return term.eval(a) + sum(term, next.eval(a), next, b);
  }

  public static int sumInts(int a, int b) {
    return sum(identity, a, inc, b);
  }

  public static int sum1(final IntUnary term, int a, final IntUnary next,
      final int b) {
    var result = 0;
    while (true) {
      if (a > b)
        return result;

      result += term.eval(a);
      a = next.eval(a);
    }
  }

  public static final IntUnary identity = i -> i;
  public static final IntUnary inc = i -> i + 1;
  public static final IntBinary sumInts1 = (i, j) -> sum1(identity, i, inc, j);

  public static final IntUnary factorial = n -> {
    if (n == 0)
      return 1;

    return n * HigherOrderFunctions.factorial.eval(n - 1);
  };

  public static int factorial1(int n) {
    var result = 1;
    while (true) {
      if (n == 0)
        return result;

      result *= n;
      n -= 1;
    }
  }

  public static final IntUnary factorial1 = HigherOrderFunctions::factorial1;

  public static void main(String... args) {
    System.out.println(sumInts1.eval(1, 1000));
    System.out.println(factorial1.eval(10));
  }

}
