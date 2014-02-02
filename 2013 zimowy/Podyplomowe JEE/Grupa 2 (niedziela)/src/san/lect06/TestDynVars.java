package san.lect06;

import san.util.DynVar;

public class TestDynVars {

  public static final DynVar<String> conn = DynVar.initially(null);
  
  public static void bar() {
    System.out.println("Działa bar() z conn: " + conn.value());
  }
  
  public static void main(String[] args) {
    bar();

    final Runnable t1 = new Runnable() {
      @Override
      public void run() {
        bar();
      }      
    };
    
    final Runnable t2 = new Runnable() {
      @Override
      public void run() {
        bar();
      }      
    };
    
    conn.binding("Połączenie 1", new Runnable() {
      @Override
      public void run() {
        new Thread(t1).start();
      }      
    });
    
    conn.binding("Połączenie 2", new Runnable() {
      @Override
      public void run() {
        new Thread(t2).start();
      }      
    });
  }

}
