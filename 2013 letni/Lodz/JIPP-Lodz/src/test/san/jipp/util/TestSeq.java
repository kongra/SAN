package test.san.jipp.util;

import san.jipp.util.LinkedSeq;
import san.jipp.util.Seq;

public class TestSeq {

  public static void main(String[] args) {
    Seq coll = LinkedSeq.EMPTY.addToFront(1).addToFront(2).addToFront(3);
    System.out.println(coll);
    
    Seq coll1 = coll.addToFront(5);
    System.out.println(coll);
    System.out.println(coll1);
    
    Seq coll2 = LinkedSeq.EMPTY.addToFront(1).addToFront(2).addToFront(3);
    System.out.println(coll.equals(coll2));
  }

}
