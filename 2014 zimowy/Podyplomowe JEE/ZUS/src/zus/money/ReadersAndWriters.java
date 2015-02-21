package zus.money;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
public class ReadersAndWriters {

  public ReadersAndWriters() {
  }

  @Lock(LockType.READ)
  public void read() {
    System.out.println("Reading:...");
  }
  
  @Lock(LockType.WRITE)
  public void write() {
    System.out.println("Writing:...");
  }
  
}
