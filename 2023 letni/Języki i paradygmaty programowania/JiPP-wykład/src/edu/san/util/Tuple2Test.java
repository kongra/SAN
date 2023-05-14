// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.util;

public class Tuple2Test {

  public static void main(String[] args) {
    var t1 = Tuple2.of(1, "245");
    var t2 = Tuple2.of(2, "762");
    var t3 = Tuple2.of(1, "245");

    System.out.println(t1.equals(t2)); // => false
    System.out.println(t2.equals(t3)); // => false
    System.out.println(t1.equals(t3)); // => true

    var t4 = foo(5);
    System.out.println(5 + t4.snd);
  }

  static Tuple2<Integer, Integer> foo(int n) {
    return Tuple2.of(n + 1, n - 1);
  }

}
