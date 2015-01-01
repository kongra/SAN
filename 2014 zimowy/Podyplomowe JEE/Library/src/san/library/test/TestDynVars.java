package san.library.test;

import java.util.Random;

import san.util.DynVar;

public class TestDynVars {

  static DynVar<Integer> x = DynVar.initially(3);
  
  static Random rnd = new Random();

  static void foo(final int y) {
    x.binding(y, new Runnable() {
      @Override
      public void run() {
        bar();
        try {
          Thread.sleep(rnd.nextInt(1000));
        }
        catch (InterruptedException e) {         
          e.printStackTrace();
        }
        if (y > 0) {
          foo(y - 1);
        }
      }
    });
  }

  static void bar() {
    System.out.println("W metodzie bar " + x.value());
  }

  public static void main(String... args) {
    // foo(5);
    // foo(7);

    new Thread(new Runnable() {
      @Override
      public void run() {
        foo(5);        
      }
    }).start();
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        foo(7);        
      }
    }).start();
    
  }

}
