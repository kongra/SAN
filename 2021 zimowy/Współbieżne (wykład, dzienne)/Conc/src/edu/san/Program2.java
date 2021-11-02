package edu.san;

public class Program2 {

  public static void main(String... args) {
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10_000_000; i++) {
        Threads.sleep(1000);
        System.out.println("t1: " + i);
      }
    });

    // t1.setDaemon(true);
    t1.start();

    Threads.join(t1);

    Threads.sleep(5000);
    System.out.println("main is done");
  }
}
