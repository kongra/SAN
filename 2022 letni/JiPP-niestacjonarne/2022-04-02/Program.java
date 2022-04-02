import java.math.BigDecimal;

class Program {

  static double foo(double x) {
    return 2 * x + 3;
  }

  static int goo(int n) {
    return 2 * n + 3;
  }

  static BigDecimal goo(BigDecimal n) {
    return n.add(new BigDecimal("4"));
  }

  public static void main(String... args) {
    // Czy procedura foo jest polimorficzna?

    int n = 10;
    foo(n); // Niejawna konwersja n z int -> double
    foo((double) n);

    double d = 10.0;
    foo(d);

    // Czy procedura goo jest polimorficzna?
    goo(5);
    goo(new BigDecimal("5"));

    int m = 10;
    double x = m;

    double y = 10;

    byte b1 = 100;

    int n2 = 100;
    // byte b2 = n2;

    byte b3 = 300;
  }

}
