package san.conc;

import san.conc.rw.Time;

public class A extends Stoppable {

  @Override
  public void run() {
    while (!isStopped()) {
      Time.sleepAtMost(1000);
      System.out.println("Działam");
    }
  }

}
