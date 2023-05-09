package edu.san;

public class Program {

  static Tuple2<Integer, Integer> foo(int n) {
    return Tuple2.of(n + 1, n % 2);
  }

  static Tuple2<Integer, Boolean> moo(int n) {
    return Tuple2.of(n + 1, n % 2 == 0);
  }

  public static void main(String... args) {
    final var result = foo(4);
    final var m = result.fst + result.snd;

    System.out.println(result);
    System.out.println(m);

    final var result1 = moo(3);
    System.out.println(result1);

    System.out.println(result.equals(result1));
  }

}
