package edu.san.conc;

public class App {

  static long opsCounter = 0;

  static class TODOs1 implements Runnable {
    @Override
    public void run() {
      for (int i = 0; i < 1000; i++) {
        // System.out.print("B");
        opsCounter += 1;
        // Threads.sleep(3);
      }
    }
  }

  public static void main(String... args) {
    final Runnable runnable1 = new TODOs1();
    final Runnable runnable2 = () -> {
      for (int i = 0; i < 2000; i++) {
        // System.out.print("C");
        opsCounter += 1;
        // Threads.sleep(2);
      }
    };

    final var thread1 = new Thread(runnable1);
    final var thread2 = new Thread(runnable2);

    thread1.start();
    thread2.start();

    for (int i = 0; i < 3000; i++) {
      // System.out.print("A");
      opsCounter += 1;
      // Threads.sleep(1);
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

    System.out.println("opsCounter is " + opsCounter);
  }

}
