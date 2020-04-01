package san.jipp.genericgame;

import java.lang.reflect.Array;

public class TestArraySeq {

  public static void main(String... args) {
    ArraySeq<Integer> a1 = new ArraySeq<>(5);
    System.out.println(a1);

    ArraySeq<Long> a2 = new ArraySeq<Long>(6)
        .add(1L).add(2L).add(3L);
    System.out.println(a2);

    ArraySeq<String> a3 = new ArraySeq<>(7
        , "abc", "xyz", "w3c", "omg", "www", "http");
    System.out.println(a3);

    System.out.println(a2.get(2) + 5);

    for (var s : a3) {
      System.out.println(s);
    }
  }

}
