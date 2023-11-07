// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  
  private static final ExecutorService pool = Executors.newFixedThreadPool(10);

  private static long n;
  
  public static void main(String[] args) {
    n = 0;
    for(var i = 0; i < 100; i++) {
      pool.execute(() -> {
        n++;
      });
    }
    
    pool.shutdown();
    
    Threads.sleep(Duration.of(3, ChronoUnit.SECONDS));
    System.out.println("n = " + n);
  }

}
