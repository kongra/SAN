// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.time.Duration;

public final class Threads {

  public interface ThrowingRunnable {

    void run() throws InterruptedException;

  }

  public static void run(ThrowingRunnable body) {
    try {
      body.run();
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }

  public static void sleep(long millis) {
    run(() -> Thread.sleep(millis));
  }

  public static void sleep(Duration duration) {
    run(() -> Thread.sleep(duration));
  }

  public static void joinAll(Iterable<Thread> threads) {
    for (var thread : threads) {
      run(thread::join);
    }
  }

  private Threads() {}

}
