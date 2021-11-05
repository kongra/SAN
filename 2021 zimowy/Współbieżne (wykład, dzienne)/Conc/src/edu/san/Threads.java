package edu.san;

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

  private Threads() {
    ;
  }
}
