package ewus.profile;

import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
public class Counter {

  private long count;

  public long incrementAndGet() {
    count += 1;
    return count;
  }

  @Remove
  public void remove() {
    System.out.println("Usuwają mnie :(");
  }
  
}
