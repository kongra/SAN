package san.conc03;

import java.util.concurrent.Semaphore;

public class Threads {

  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void withSemaphore(Semaphore s, Runnable body) {
    try {
      s.acquire();
      try {
        body.run();
      }
      finally {
        s.release();
      }
    }
    catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private Threads() {

  }

}
