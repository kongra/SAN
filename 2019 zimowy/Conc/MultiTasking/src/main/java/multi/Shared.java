package multi;// Copyright (c) Konrad Grzanek
// Created 13.10.2019

import java.util.concurrent.Semaphore;

public class Shared {

  static Semaphore s1 = new Semaphore(1, true);

  static long n = 100_000;

  public static void main(String... args) {
    new Thread(() -> {
      System.out.println("Wątek 1");
      try {
        Thread.sleep(1000);
        s1.acquire();
        n += 1;
        s1.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      System.out.println("Wątek 2");
      try {
        s1.acquire();
        n -= 1;
        s1.release();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }

}
