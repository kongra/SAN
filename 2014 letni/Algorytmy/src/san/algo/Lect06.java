package san.algo;

import san.fn.Unary;

public class Lect06 {

  public static void main(String[] args) {
    Unary f = new Unary() {
      @Override
      public Object call(Object arg) {
        Integer i = (Integer) arg;
        return i * i;
      }
    };

    Object lst1 = List.create(1, 2, 3, 4, 5, 6, 7, 8);
    Object lst2 = List.map(f, lst1);

    System.out.println(List.show(lst1));
    System.out.println(List.show(lst2));
  }

}
