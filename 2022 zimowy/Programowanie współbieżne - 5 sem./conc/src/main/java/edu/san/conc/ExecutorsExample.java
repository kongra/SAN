// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.conc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class ExecutorsExample {

  static ExecutorService pool = Executors.newFixedThreadPool(8);

  public static void main(String[] args) {
    Future<String> result = pool.submit(() -> {
      System.out.println("Rozpoczynam działanie...");
      Threads.sleep(3_000);
      System.out.println("Działanie zakończone.");
      return "completed";
    });

    System.out.println("Odebrałem " + result);

    try {
      var value = result.get(3, TimeUnit.SECONDS);
      System.out.println(value);
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
      e.printStackTrace();
    }

    pool.shutdown();
    System.out.println("Wątek main zakończył działanie");
  }

}
