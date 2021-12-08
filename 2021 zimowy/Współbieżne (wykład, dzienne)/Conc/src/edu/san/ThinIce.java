package edu.san;

import java.util.concurrent.Semaphore;

public class ThinIce {

  private Semaphore s1 = new Semaphore(1, true);

  public void runme1(Runnable body) {
    synchronized (this) {
      body.run();
    }
  }

  public void runme2(Runnable body) {
    try {
      s1.acquire();
      body.run();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      s1.release();
    }
  }

  public static void main(String... args) {
    ThinIce vanillaIce = new ThinIce();

    // Thread t1 = new Thread(() -> {
    // vanillaIce.runme2(() -> {
    // vanillaIce.runme2(() -> {
    // System.out.println("HEHE");
    // });
    // });
    // });

    // t1.start();
    // System.out.println("t1 started");

    Thread t1 = new Thread(() -> {
      vanillaIce.runme1(() -> {
        Thread t2 = new Thread(() -> {
          vanillaIce.runme1(() -> {
            System.out.println("HEHE");
          });
        });
        t2.start();
        System.out.println("t2 started");
        try {
          t2.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    });

    t1.start();
    System.out.println("t1 started");

  }

}
