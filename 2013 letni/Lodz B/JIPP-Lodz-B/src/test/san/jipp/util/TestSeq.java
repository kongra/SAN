package test.san.jipp.util;

import san.jipp.util.LinkedSeq;
import san.jipp.util.Seq;

public class TestSeq {

  public static void main(String[] args) {
    Seq coll = LinkedSeq.EMPTY.addToFront(4).addToFront(3).addToFront(1);
    System.out.println(coll);
    
    System.out.println(LinkedSeq.EMPTY);
    System.out.println(LinkedSeq.EMPTY.first());
    System.out.println(LinkedSeq.EMPTY.rest() == LinkedSeq.EMPTY);
    
    Seq coll1 = LinkedSeq.EMPTY.addToFront(4).addToFront(3).addToFront(1);
    
    System.out.println(coll1.equals(coll));
    
    System.out.println(LinkedSeq.EMPTY.hashCode());
    
    System.out.println(coll.hashCode());
    System.out.println(coll1.hashCode());
  }

}
