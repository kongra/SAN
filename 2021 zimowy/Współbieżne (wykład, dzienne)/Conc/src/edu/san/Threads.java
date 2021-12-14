package edu.san;

import java.util.concurrent.Semaphore;

public final class Threads {

  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void join(Thread... threads) {
    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void wait(Object obj) {
    try {
      synchronized (obj) {
        obj.wait();
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void withSemaphore(Semaphore s, Runnable body) {
    boolean acquired = false;
    try {
      s.acquire(); // s.wait();
      acquired = true;
      body.run();
    } catch (Exception e) {
      throw new RuntimeException(e);
    } finally {
      if (acquired) {
        s.release(); // s.signal();
      }
    }
  }

  private Threads() {
    ;
  }
}
