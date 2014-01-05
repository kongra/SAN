package san.jee.lect06;

import san.util.DynVar;

public class TestDynVar {

  public static final DynVar<Integer> wartość = DynVar.initially(5);

  static void foo() {
    System.out.println(wartość.value());
  }

  public static void main(String[] args) {
    foo();

    wartość.binding(7, new Runnable() {
      @Override
      public void run() {
        foo();

        wartość.binding(86, new Runnable() {
          @Override
          public void run() {
            foo();
          }
        });

        foo();
      }
    });

    foo();
  }

}
