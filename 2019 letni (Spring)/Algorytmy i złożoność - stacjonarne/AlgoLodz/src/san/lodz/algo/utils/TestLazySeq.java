package san.lodz.algo.utils;

import static san.lodz.algo.utils.LazySeq.iterate;
import static san.lodz.algo.utils.LazySeq.take;

public class TestLazySeq {

  public static void main(String... args) {
    // Seq<Integer> N = iterate(n -> n + 1, 0);
    // System.out.println(take(100, N).asString());

//    System.out.println(N.first());
//    System.out.println(N.rest().first());
//    System.out.println(N.rest().rest().first());
//    System.out.println(N.rest().rest().rest().first());

    Seq<String> s1 = LinkedSeq.make("a", "b", "c", "a", "d", "b", "a");
    Seq<String> s2 = s1.filter(s -> s.equals("a") || s.equals("b"));
    System.out.println(s2.asString());
  }

}
