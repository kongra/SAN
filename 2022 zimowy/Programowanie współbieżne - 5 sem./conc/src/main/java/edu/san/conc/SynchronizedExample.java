package edu.san.conc;

public class SynchronizedExample {

  static long opsCounter;

  static synchronized void updateCounter(int delta) {
    opsCounter += delta;
    counterValue();
  }

  static synchronized long counterValue() {
    return opsCounter;
  }

  static class TODOs1 implements Runnable {
    @Override
    public void run() {
      for (var i = 0; i < 1000; i++) {
        updateCounter(1);
      }
    }
  }

  public static void main(String... args) {
    final Runnable runnable2 = () -> {
      for (var i = 0; i < 2000; i++) {
        updateCounter(1);
      }
    };
    final var thread2 = new Thread(runnable2);

    final Runnable runnable1 = new TODOs1();
    final var thread1 = new Thread(runnable1);

    thread1.start();
    thread2.start();

    for (var i = 0; i < 3000; i++) {
      updateCounter(1);
    }

    try {
      thread1.join();
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
    try {
      thread2.join();
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("opsCounter is " + counterValue());
  }

}
