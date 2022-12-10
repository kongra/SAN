package edu.san.conc;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public final class Threads {

  public static <T> T interruptibly(InterruptibleSupplier<T> supplier) {
    try {
      return supplier.get();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      return null; // Exceptions.fail(e);
    }
  }

  public static void join(Thread t) {
    interruptibly(() -> {
      t.join();
      return null;
    });
  }

  public static void sleep(long millis) {
    interruptibly(() -> {
      Thread.sleep(millis);
      return null;
    });
  }

  public static void sleep(Duration duration) {
    interruptibly(() -> {
      Thread.sleep(duration);
      return null;
    });
  }

  public static void sleep(long timeout, TimeUnit timeUnit) {
    interruptibly(() -> {
      timeUnit.sleep(timeout);
      return null;
    });
  }

  private Threads() {
  }

}
