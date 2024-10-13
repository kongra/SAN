package edu.san.concurrency;

class Program {

  static final long counter = 0;

  public static void main(String... args) {
    System.out.println("Before: " + counter);
    runExperiment();
    System.out.println("After: " + counter);
  }

  static void runExperiment() {
    var t1 = new Thread(() -> {
      for (int i = 0; i < 10_000_000; i++) {
        counter++; // counter = counter + 1
      }
      System.out.println("after t1.for: " + counter);
    });

    var t2 = new Thread(() -> {
      for (int i = 0; i < 10_000_000; i++) {
        counter++; // counter = counter + 1
      }
      System.out.println("after t2.for: " + counter);
    });

    t1.start();
    t2.start();

    try {
      t1.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    try {
      t2.join();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

  }
}