package test.san.jipp.util;

import san.jipp.util.Functions;
import san.jipp.util.LinkedSeq;
import san.jipp.util.Seq;

public class TestSeq {

  public static void main(String[] args) {
    Seq<Object> coll = LinkedSeq.withElements(1, 2, 3, 4, 5);
    System.out.println(coll);

    // Integer sum = Functions.reduce(coll, new Functions.Binary<Integer>() {
    // @Override
    // public Integer call(Integer lhs, Integer rhs) {
    // return lhs + rhs;
    // }
    // }, 0);
    //
    // System.out.println(sum);
    // }

  }

}
