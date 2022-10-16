package edu.san.conc;

public class App {

  static class Task1 implements Runnable {
    @Override
    public void run() {
      while (true) {
        System.out.print("B");
        Threads.sleep(1_500);
      }
    }
  }

  public static void main(String... args) {
    Runnable task1 = new Task1();
    Runnable task2 = () -> {
      while (true) {
        System.out.print("C");
        Threads.sleep(800);
      }
    };

    Thread thread1 = new Thread(task1);
    Thread thread2 = new Thread(task2);

    thread1.start();
    thread2.start();

    while (true) {
      System.out.print("A");
      Threads.sleep(1_000);
    }
  }

}
