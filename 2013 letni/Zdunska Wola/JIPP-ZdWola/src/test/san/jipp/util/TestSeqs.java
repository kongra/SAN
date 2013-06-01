package test.san.jipp.util;

import san.jipp.util.Func;
import san.jipp.util.LinkedSeq;
import san.jipp.util.Seq;

public class TestSeqs {

  public static void main(String[] args) {
    Seq s = LinkedSeq.withElements(3, 4, 7, 5, 6.234); 
    System.out.println(s);
    
    Integer sum = (Integer) Func.reduce(s, Func.IntegerPLUS, 0);
    System.out.println(sum);
  }

}
