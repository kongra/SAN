// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.ArrayList;

public class Program2 {

  static long n;

  static Object obj = new Object();
  
  public static void main(String[] args) {
    n = 0L;
    final var threads = new ArrayList<Thread>();
    
    long start = System.currentTimeMillis();
    for (var i = 0; i < 100; i++) {
      var thread = new Thread(() -> {
        for (var j = 0; j < 10_000; j++) {
          synchronized (obj) { // RE-ENTRANT
            // n++
            long n1 = n;
            long n2 = n1 + 1;
            n = n2;
          }
        }
      });

      threads.add(thread);
      thread.start();
    }

    Threads.joinAll(threads);
    System.out.println("Finished with n = " + n + " in " +
        (System.currentTimeMillis() - start) + " msecs.");

    // * RACE CONDITION(S)

  }
}
