package san.fp1;

public class Test1 {

  public static int identity(int n) {
    return n;
  }

  public static final IUnary identity1 = n -> n;
  public static final IUnary inc1 = n -> n + 1;

  public static int inc(int n) {
    return n + 1;
  }

  public static int sum(final IUnary term, final int a, final IUnary next,
      final int b) {
    while (true) {
      if (a > b)
        return 0;

      return term.eval(a) + sum(term, a + 1, next, b);
    }
  }

  public static int sumInts(final int a, final int b) {
    return sum(Test1.identity1, a, Test1.inc1, b);
  }

  public static void main(String... args) {
    System.out.println(sumInts(0, 10));
  }
}
