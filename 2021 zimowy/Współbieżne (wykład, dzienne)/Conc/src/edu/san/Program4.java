package edu.san;

public class Program4 {

  public static void main(String... args) {
    Counter c1 = new Counter();

    Thread[] threads = new Thread[10];
    for (int i = 0; i < threads.length; i++) {
      threads[i] = new Thread(() -> {
        for (int j = 0; j < 1_000; j++) {
          c1.inc();
        }
      });

      threads[i].start();
    }

    for (Thread thread : threads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println("c1.value is " + c1.value());
  }

}
