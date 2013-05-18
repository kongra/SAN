package test.san.jipp.util;

import san.jipp.util.LinkedSeq;
import san.jipp.util.Seq;
import san.jipp.util.SeqTools;

public class TestSeq {

  public static void main(String[] args) {
    Seq<Integer> coll = LinkedSeq.create(1, 2, 3, 4, 5, 6);
    System.out.println(coll);

    Integer sum = SeqTools.reduce(coll, new SeqTools.BinaryFn<Integer>() {
      @Override
      public Integer call(Integer lhs, Integer rhs) {
        return lhs + rhs;
      }
    }, 0);

    // Integer sum = (Integer) SeqTools.reduce(coll, new SeqTools.BinaryFn() {
    // @Override
    // public Object call(Object lhs, Object rhs) {
    // int a = (Integer) lhs;
    // int b = (Integer) rhs;
    //
    // return a + b;
    // }
    // }, 0);
  }

}
