package edu.san;

import java.util.concurrent.Semaphore;

public class Deadlocks {

  public static void main(String... args) {
    // test1();
    test2();

    System.out.println("main KONIEC");
  }

  private static void test2() {
    Object m1 = new Object();

    Thread t1 = new Thread(() -> {
      synchronized (m1) {
        System.out.println("Jestem w sekcji krytycznej");
        Threads.sleep(1000);
        synchronized (m1) {
          System.out.println("Dostałem m1 po raz drugi");
        }
        System.out.println("Opuszczam sekcję krytyczną");
      }
    });

    t1.start();

    try {
      t1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void test1() {
    // Semaphore IS NOT RE-ENTRANT
    // Monitors ARE RE-ENTRANT
    Semaphore s1 = new Semaphore(1, true);

    Thread t1 = new Thread(() -> {

      try {
        s1.acquire();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println("Jestem w sekcji krytycznej");
      Threads.sleep(1000);

      try {
        s1.acquire();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println("Wywołuję s1.release()");
      s1.release();
    });

    t1.start();

    try {
      t1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
