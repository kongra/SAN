package test.san.jdbc.open;

import san.jdbc.open.OObj;
import san.jdbc.open.OProp;
import san.jdbc.open.OType;
import junit.framework.TestCase;

public class TestOpen extends TestCase {

  public void test() {
    OType Person = OType.ofName("Person");
    OProp name = OProp.ofName("name");
    
    OObj p = Person.create();
    p.set(name, "Jan");
    p.set("age", 40);
    
    
    System.out.println(p.get("name"));
    System.out.println(p.get("age"));
  }
  
}
