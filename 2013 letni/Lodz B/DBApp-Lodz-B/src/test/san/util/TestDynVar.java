package test.san.util;

import san.util.Body;
import san.util.DynVar;
import junit.framework.TestCase;

public class TestDynVar extends TestCase {

  public void test() {
    final DynVar<Integer> var1 = DynVar.initially(34);

    var1.binding(245, new Runnable() {
      @Override
      public void run() {
        System.out.println(var1.value());
      }
    });
    
    var1.binding(245, Body.pass);
    
    var1.binding(245, new Body() {
      @Override
      public void run() throws Break, Continue {
        System.out.println(var1.value());
      }
    });
  }

}
