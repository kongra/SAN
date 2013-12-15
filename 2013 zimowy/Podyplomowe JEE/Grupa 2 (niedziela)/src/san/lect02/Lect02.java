package san.lect02;

import san.coll.ISeq;
import san.coll.Utils;
import san.fn.Delay;
import san.fn.Nullary;

public class Lect02 {

  public static void main(String[] args) {
    // test01();
    // test02();

    ISeq n = Utils.naturals();
    // System.out.println(Utils.reduce(Binary.LONG_ADD, Utils.take(10, n)));
    for (Object object : Utils.take(10, n)) {
      System.out.println(object);
    }
    
  }

  private static void test02() {
    Delay wartość = Delay.of(new Nullary() {
      @Override
      public Object call() {
        System.out.println("Zaczynam bardzo długie obliczenia (2h)");
        System.out.println("Zakończyłem obliczenia");
        return 256;
      }
    });

    System.out.println(wartość.call());
  }

//  private static void test01() {
//    Complex a = Complex.planar(1, 2);
//    Complex b = Complex.polar(4, Math.PI / 4);
//
//    System.out.println(Utils.sumReals(a, b));
//  }

}
