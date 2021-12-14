package edu.san;

import java.util.concurrent.Semaphore;

public class ThinIce {

  private Semaphore s1 = new Semaphore(1, true);

  public void runme1(Runnable body) {
    synchronized (this) {
      body.run(); // Sekcja krytyczna 1
    }
  }

  public void runme2(Runnable body) {
    try {
      s1.acquire();
      body.run(); // Sekcja krytyczna 2
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      s1.release();
    }
  }

  public static void main(String... args) {
    ThinIce vanillaIce = new ThinIce();

    Thread parentThread = new Thread(() -> {
      vanillaIce.runme2(() -> {
        Thread childThread = new Thread(() -> {
          vanillaIce.runme2(() -> {
            System.out.println("HEHE");
          });
        });
        childThread.start();
        System.out.println("t2 started");
        Threads.join(childThread);
      });
    });

    parentThread.start();
    System.out.println("t1 started");

    // Q: Co jest nie tak?
    // A1: Tworzenie wątków w dowolnym miejscu jest katastrofą
    // A2: Nie wolno w sekcji krytycznej wywoływać CUDZEGO, NIEZNANEGO KODU, np.
    // Runnable.run()
  }

}
