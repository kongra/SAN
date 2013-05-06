package test.san.util;

import san.util.DynVar;
import junit.framework.TestCase;

public class TestDynVar extends TestCase {

  public static final DynVar<Integer> var1 = DynVar.initially(34);
  
  public static void foo() {
    System.out.println("W wywołaniu foo () " + var1.value());
  }
  
  public static void bar() {
    System.out.println("W wywołaniu bar () " + var1.value());
  }
  
  public void test() {
    System.out.println("Przed wywołaniami " + var1.value());
    
    var1.binding(15, new Runnable() {
      @Override
      public void run() {
        foo();
        
        var1.binding(23, new Runnable(){
          @Override
          public void run() {
            bar();            
          }          
        });
      }      
    });
    
    foo();
    bar();
    
    System.out.println("Po wywołaniach " + var1.value());
  }

}
