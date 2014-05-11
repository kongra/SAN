package san.algo;

import san.fn.Delay;
import san.fn.Nullary;
import san.fn.Square;

public class Lect05 {

  public static void main(String[] args) {
    ISeq s = LinkedSeq.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    // System.out.println(Seqs.count(s));

    Nullary value = Delay.of(new Nullary() {
      @Override
      public Object call() {
        System.out.println("Bardzo długie obliczenia...");
        return 256;
      }
    });
    
    System.out.println(value);
    System.out.println(value.call());
    System.out.println(value.call());
    
    ISeq s1 = Seqs.map(new Square(), s);
    Seqs.print(s1);
  }

}
