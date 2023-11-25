// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Program6 {

  static volatile boolean isRunning = true;
  
  public static void main(String[] args) {
    final var threads = new ArrayList<Thread>();

    final var start = System.currentTimeMillis();
    
    AtomicLong n = new AtomicLong(0L);
    
    final var thread1 = new Thread(() -> {
      while (isRunning) {
        n.incrementAndGet();
      }
    });
    
    threads.add(thread1);
    thread1.start();
    
    final var thread2 = new Thread(() -> {
      Threads.sleep(1000L);
      isRunning = false;
    });
    
    threads.add(thread2);
    thread2.start();
    
    
    Threads.joinAll(threads);
    System.out.println("Finished with n = " + n.get() + " in " +
        (System.currentTimeMillis() - start) + " msecs.");

  }

}
