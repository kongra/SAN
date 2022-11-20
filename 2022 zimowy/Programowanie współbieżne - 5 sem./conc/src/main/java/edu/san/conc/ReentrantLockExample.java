package edu.san.conc;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

  static long opsCounter;
  static ReentrantLock lock1 = new ReentrantLock(true);
  
  static void updateCounter(int delta) throws InterruptedException {
    lock1.lock();
    try {
      opsCounter += delta;
      counterValue();
    } finally {
      lock1.unlock();
    }
  }

  static long counterValue() throws InterruptedException {
    lock1.lock();
    try {
      return opsCounter;
    } finally {
      lock1.unlock();
    }
  }

  static class TODOs1 implements Runnable {
    @Override
    public void run() {
      for (var i = 0; i < 1000; i++) {
        try {
          updateCounter(1);
        } catch (final InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  public static void main(String... args) {    
    final Runnable runnable2 = () -> {
      for (var i = 0; i < 2000; i++) {
        try {
          updateCounter(1);
        } catch (final InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    };
    final var thread2 = new Thread(runnable2);

    final Runnable runnable1 = new TODOs1();
    final var thread1 = new Thread(runnable1);
       
    thread1.start();
    thread2.start();

    for (var i = 0; i < 3000; i++) {
      try {
        updateCounter(1);
      } catch (final InterruptedException e) {
        throw new RuntimeException(e);
      }
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

    try {
      System.out.println("opsCounter is " + counterValue());
    } catch (final InterruptedException e) {
      e.printStackTrace();
    }
  }

}
