package san.conc;

import java.util.concurrent.Semaphore;

public class Threads {

  public static void acquiring(Semaphore s, int permits, Runnable body) {
    try {
      s.acquire(permits);
      try {
        body.run();
      }
      finally {
        s.release(permits);
      }
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void acquiring(Semaphore s, Runnable body) {
    acquiring(s, 1, body);
  }

}
