package san.jee.lect02;

import san.coll.ISeq;
import san.coll.LinkedSeq;

public class TestISeq {

  public static void main(String[] args) {
    ISeq coll = LinkedSeq.create(1, 2, 3, 4);
    System.out.println(coll);
  }

}
