// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Program3 {

  static long n;

  static ReentrantLock lock = new ReentrantLock(false);

  public static void main(String[] args) {
    n = 0L;
    final var threads = new ArrayList<Thread>();

    final var start = System.currentTimeMillis();
    for (var i = 0; i < 100; i++) {
      final var thread = new Thread(() -> {
        for (var j = 0; j < 10_000; j++) {
//          lock.lock();
//          try {
//            // n++
//            long n1 = n;
//            long n2 = n1 + 1;
//            n = n2;
//          } finally {
//            lock.unlock();
//          }

          Threads.locking(lock, (Runnable) () -> {
            final var n1 = n;
            final var n2 = n1 + 1;
            n = n2;
          });

        }
      });

      threads.add(thread);
      thread.start();
    }

    Threads.joinAll(threads);
    System.out.println("Finished with n = " + n + " in " +
        (System.currentTimeMillis() - start) + " msecs.");
  }
}
