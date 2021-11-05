package edu.san;

/**
 * @author kongra
 */
public class Program3 {

  public static void main(String... args) {
    Object obj = new Object();

    Thread soldier1 = new Thread(() -> {
      System.out.println("soldier1: Wywołuję WAIT");
      Threads.wait(obj);
      System.out.println("soldier1: Obudziłem się");
    });

    Thread soldier2 = new Thread(() -> {
      System.out.println("soldier2: Wywołuję WAIT");
      Threads.wait(obj);
      System.out.println("soldier2: Obudziłem się");
    });

    Thread soldier3 = new Thread(() -> {
      System.out.println("soldier3: Wywołuję WAIT");
      Threads.wait(obj);
      System.out.println("soldier3: Obudziłem się");
    });

    soldier1.start();
    soldier2.start();
    soldier3.start();

    Thread colonel = new Thread(() -> {
      System.out.println("colonel: START");
      Threads.sleep(2_000);

      System.out.println("colonel: Wywołuję NOTIFY");
      synchronized (obj) {
        obj.notify();
      }
      System.out.println("colonel: KONIEC");
    });

    colonel.start();

    System.out.println("main: KONIEC");
  }

}
