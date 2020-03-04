package san.jipp;

import san.jipp.types.Age;

public class Program1 {

  static double foo(int n) {
    return 4 * n + 3;
  }

  static double foo(double x) {
    return 2 * x + 3;
  }

  public static void main(String... args) {
    int n = 1;
    foo(n);     //   1 : int
    foo(0.5); // 0.5 : double

    Object s = "abc";
    Age age = Age.of(10).get();
    System.out.println(s);
    System.out.println(age);
  }

}
