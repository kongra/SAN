package test.san.jipp.util;

import san.jipp.util.LinkedSeq;
import san.jipp.util.Seq;

public class TestSeqs {

  public static void main(String[] args) {
    Seq s = LinkedSeq.EMPTY.addToFront(1).addToFront(2).addToFront(3);
    
    System.out.println(s);
  }

}
