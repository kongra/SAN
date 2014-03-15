package ewus.profile;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Println {

  @AroundInvoke
  public Object logMethod(InvocationContext ic) {
    System.out.println("Przed wywołaniem...");
    try {
      return ic.proceed();
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
    finally {
      System.out.println("Po wywołaniu");
    }
  }
  
}
