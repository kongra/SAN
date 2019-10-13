// Copyright (c) Konrad Grzanek
// Created 13.10.2019

import java.util.concurrent.*;

public class Program {

  static ExecutorService threadPool =
      Executors.newFixedThreadPool(150);

  public static void main(String... args) {
    Future<Integer> result = threadPool.submit(() -> {
      System.out.println("Działają dłuugie obliczenia...");
      Thread.sleep(2000);
      return 5;
    });

    Runnable r1 = () -> {
      System.out.println("Działa wątek " +
          Thread.currentThread().getName());
    };
    Thread t1 = new Thread(r1);
    t1.start();

    new Thread(() -> {
      System.out.println("Działa wątek " +
          Thread.currentThread().getName());
    }).start();

    System.out.println("Działa wątek main");

    try {
      System.out.println(result.get(3000, TimeUnit.MILLISECONDS));
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

}
