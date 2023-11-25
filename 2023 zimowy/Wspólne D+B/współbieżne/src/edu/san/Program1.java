// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Program1 {

  static long n;

  static Semaphore s = new Semaphore(1, false);

  public static void main(String[] args) {
    n = 0;
    final var threads = new ArrayList<Thread>();

    long start = System.currentTimeMillis();
    for (var i = 0; i < 100; i++) {
      var thread = new Thread(() -> {
        for (var j = 0; j < 10_000; j++) {
//          Threads.run(s::acquire);
//          try {
//            // n++;
//            long n1 = n;
//            long n2 = n1 + 1;
//            n = n2;
//          } finally {
//            s.release();
//          }
          
          Threads.acquiring(s, () -> {
            long n1 = n;
            long n2 = n1 + 1;
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

    // * RACE CONDITION(S)
    // * NOT RE-ENTRANT

  }

}
