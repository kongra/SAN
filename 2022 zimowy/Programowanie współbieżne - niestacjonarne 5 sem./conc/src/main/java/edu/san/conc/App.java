package edu.san.conc;

import java.util.concurrent.Semaphore;

public class App {

  static long opsCounter;
  static Semaphore sem1 = new Semaphore(1, true);

  static void updateCounter(int delta) throws InterruptedException {
    sem1.acquire();
    try {
      opsCounter += delta;
    } finally {
      sem1.release();
    }
  }

  static long counterValue() throws InterruptedException {
    sem1.acquire();
    try {
      return opsCounter;
    } finally {
      sem1.release();
    }
  }

  static class TODOs1 implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i < 1000; i++) {
        // System.out.print("B");
        // opsCounter += 1;
        // Threads.sleep(3);
        try {
          updateCounter(1);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  public static void main(String... args) {
    final Runnable runnable1 = new TODOs1();
    final Runnable runnable2 = () -> {
      for (int i = 0; i < 2000; i++) {
        // System.out.print("C");
        // opsCounter += 1;
        // Threads.sleep(2);
        try {
          updateCounter(1);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    };

    final var thread1 = new Thread(runnable1);
    final var thread2 = new Thread(runnable2);

    thread1.start();
    thread2.start();

    for (int i = 0; i < 3000; i++) {
      // System.out.print("A");
      // opsCounter += 1;
      // Threads.sleep(1);
      
      try {
        updateCounter(1);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    try {
      thread1.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      System.out.println("opsCounter is " + counterValue());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
