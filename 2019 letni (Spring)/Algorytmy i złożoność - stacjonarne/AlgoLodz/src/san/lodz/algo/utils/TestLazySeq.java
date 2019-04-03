package san.lodz.algo.utils;

import static san.lodz.algo.utils.LazySeq.iterate;
import static san.lodz.algo.utils.LazySeq.take;

public class TestLazySeq {

  public static void main(String... args) {
    Seq<Integer> N = iterate(n -> n + 1, 0);
    System.out.println(take(100, N).asString());

//    System.out.println(N.first());
//    System.out.println(N.rest().first());
//    System.out.println(N.rest().rest().first());
//    System.out.println(N.rest().rest().rest().first());
  }

}
