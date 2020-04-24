package san.jipp;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import san.jipp.types.Age;

import java.util.List;

public class Program1 {

  static double foo(int n) {
    return 4 * n + 3;
  }

  static double foo(double x) {
    return 2 * x + 3;
  }

  @Contract(pure = true)
  static <T extends Number> double sum(@NotNull List<T> xs) {
    double result = 0;
    for(var x : xs) {
      result = Double.sum(result, x.doubleValue());
    }
    return result;
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
