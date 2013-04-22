package test.san.util;

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
  }

}
