// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.List;

public class TestTuple3 {

  public static void main(String[] args) {
    final var t1 = Tuple3.of(123, "abc", List.of(3.14, 2.71));
    System.out.println(t1);
    System.out.println(t1.thrd());

    final var t2 = Tuple3.of(123, "abc", List.of(3.14, 2.71));
    System.out.println(t1.equals(t2));
  }

}
