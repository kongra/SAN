// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.conc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CompletableFutureExample {

  static ExecutorService pool = Executors.newFixedThreadPool(8);

  public static void main(String[] args) {
    var result = CompletableFuture.supplyAsync(
        () -> {
          System.out.println("Rozpoczynam działanie...");
          Threads.sleep(3_000);
          System.out.println("Działanie zakończone.");
          return "completed";
        }, pool).thenAcceptAsync(s -> {
          System.out.println("Odebrałem " + s);
        }, pool);

    System.out.println("Odebrałem " + result);
    // pool.shutdown();
    System.out.println("Wątek main zakończył działanie");

  }

}
