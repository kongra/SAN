package san.jee.lect02;

import san.coll.ISeq;
import san.coll.LinkedSeq;
import san.coll.Utils;
import san.coll.fn.NoArg;
import san.coll.fn.Delay;
import san.coll.fn.Unary;

public class TestISeq {

  public static void main(String[] args) {
    // testLinkedSeq();
    // testNaturals();
    // testPromise();
    // testMap();

    // ISeq coll = LinkedSeq.create(1, 2, 3, 4, 5);
    // Integer suma = (Integer) Utils.reduce(Utils.PLUS, 0, coll);
    // System.out.println(suma);

    ISeq coll = Utils.repeat(2);

    Utils.doSeq(coll, 15, new Unary() {
      @Override
      public Object call(Object element) {
        System.out.println(element);
        return null;
      }
    });

    coll = Utils.repeatedly(new NoArg() {
      @Override
      public Object call() {
        return 5;
      }
    });

    Utils.doSeq(coll, 15, new Unary() {
      @Override
      public Object call(Object element) {
        System.out.println(element);
        return null;
      }
    });
  }

  private static void testMap() {
    ISeq coll = LinkedSeq.create(1, 2, 3, 4, 5);
    System.out.println(coll);

    ISeq newColl = Utils.map(new Unary() {
      @Override
      public Object call(Object param) {
        return (Integer) param * 2;
      }
    }, coll);

    System.out.println(newColl);
  }

  private static void testPromise() {
    Delay p = Delay.create(new NoArg() {
      @Override
      public Object call() {
        System.out.println("Bardzo długie obliczenia...");
        return 4;
      }
    });

    p.call();
    p.call();
  }

  private static void testLinkedSeq() {
    ISeq coll = LinkedSeq.create(1, 2, 3, 4);
    System.out.println(coll);
  }

  private static void testNaturals() {
    ISeq<Long> naturals = Utils.integers(-10);

    Utils.doSeq(naturals, 5, new Unary<Void, Long>() {
      @Override
      public Void call(Long param) {
        System.out.println(param);
        return null;
      }
    });
  }

}
