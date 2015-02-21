package zus.money;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
public class RatesImport {

  @Schedule(minute="21", hour="13")
  public void doImport() {
    System.out.println("Import starts: ");
  }
  
}
