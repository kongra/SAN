package san.conc.rw;

import java.util.concurrent.Semaphore;

import san.conc.Threads;

public class Board {

  private final Semaphore s = new Semaphore(Integer.MAX_VALUE, true);

  public void write() {
    Threads.acquiring(s, Integer.MAX_VALUE, new Runnable() {
      @Override
      public void run() {
        Time.sleepAtMost(400);
      }
    });
  }

  public void read() {
    Threads.acquiring(s, 1, new Runnable() {
      @Override
      public void run() {
        Time.sleepAtMost(150);
      }
    });
  }
}
