package edu.san;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Railways {

  static ExecutorService executorService = Executors.newFixedThreadPool(4);

  public static void main(String... args) {
    var sem1 = new Semaphore(1, true);

    executorService.execute(() -> {
      Threads.withSemaphore(sem1, () -> {
        System.out.println("Train 1: JESTEM W SEKCJI KRYTYCZNEJ");
        // try {
        // sem1.acquire();
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        Threads.sleep(2000);
        // throw new RuntimeException("Train 1: ERR");
        System.out.println("Train 1: OPUSZCZAM SEKCJĘ KRYTYCZNĄ");
      });
    });

    executorService.execute(() -> {
      Threads.withSemaphore(sem1, () -> {
        System.out.println("Train 2: JESTEM W SEKCJI KRYTYCZNEJ");
        Threads.sleep(1000);
        System.out.println("Train 2: OPUSZCZAM SEKCJĘ KRYTYCZNĄ");
      });
    });

    executorService.shutdown();
  }

}
