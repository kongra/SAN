// Copyright (c) Konrad Grzanek
// Created 24.10.2020

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {

  private static ExecutorService executorService =
      Executors.newFixedThreadPool(10);

  public static void main(String... args) {
    System.out.println("Zaczynam " + Thread.currentThread());

    Thread t1 = new Thread(() -> {
      System.out.println("Jestem sobie " + Thread.currentThread());
      for(int i = 0; i < 4; i++) {
        ThreadTools.sleep(1000);
        System.out.println("Działam dalej " + Thread.currentThread());
      }
    });
    // t1.setDaemon(true);
    t1.start();

    Thread t2 = new Thread(() -> {
      System.out.println("Jestem sobie " + Thread.currentThread());
      for(int i = 0; i < 10; i++) {
        ThreadTools.sleep(1000);
        System.out.println("Działam dalej " + Thread.currentThread());
      }
    });
    // t2.setDaemon(true);
    t2.start();

    executorService.submit(() -> {
      System.out.println("Jestem sobie " + Thread.currentThread());
      for(int i = 0; i < 5; i++) {
        ThreadTools.sleep(1000);
        System.out.println("Działam dalej " + Thread.currentThread());
      }
    });

    ThreadTools.run(t1::join);
    ThreadTools.run(t2::join);
    System.out.println("Kończę " + Thread.currentThread());
    executorService.shutdown();

//    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//      executorService.shutdown();
//    }));
  }
}
