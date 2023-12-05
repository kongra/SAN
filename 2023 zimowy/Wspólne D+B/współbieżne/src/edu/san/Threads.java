// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.time.Duration;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public final class Threads {

  @FunctionalInterface
  public interface ThrowingRunnable {

    void run() throws InterruptedException;

  }

  public static void run(ThrowingRunnable body) {
    try {
      body.run();
    } catch (final InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }

  public static void acquiring(Semaphore s, Runnable body) {
    acquiring(s, 1, body);
  }

  public static void acquiring(Semaphore s, int permits,
      Runnable body) {
    run(() -> s.acquire(permits));
    try {
      body.run();
    } finally {
      s.release(permits);
    }
  }

  public static void locking(Lock lock, Runnable body) {
    lock.lock();
    try {
      body.run();
    } finally {
      lock.unlock();
    }
  }

  public static void locking(Lock lock, ThrowingRunnable body) {
    lock.lock();
    try {
      run(body);
    } finally {
      lock.unlock();
    }
  }

  public static void sleep(long millis) {
    run(() -> Thread.sleep(millis));
  }

  public static void sleep(Duration duration) {
    run(() -> Thread.sleep(duration));
  }

  public static void joinAll(Iterable<Thread> threads) {
    for (final var thread : threads) {
      run(thread::join);
    }
  }

  public static Thread startNew(Runnable body) {
    final var t = new Thread(body);
    t.start();
    return t;
  }

  private Threads() {}

}
