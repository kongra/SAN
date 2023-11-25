// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Program4 {

  static final AtomicLong n = new AtomicLong();

  public static void main(String[] args) {
    n.set(0);
    final var threads = new ArrayList<Thread>();

    final var start = System.currentTimeMillis();
    for (var i = 0; i < 100; i++) {
      final var thread = new Thread(() -> {
        for (var j = 0; j < 10_000; j++) {
          n.incrementAndGet();
        }
      });

      threads.add(thread);
      thread.start();
    }

    Threads.joinAll(threads);
    System.out.println("Finished with n = " + n.get() + " in " +
        (System.currentTimeMillis() - start) + " msecs.");
  }
}
