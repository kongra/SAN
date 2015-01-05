package san.conc03;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

  static final Random rnd = new Random();

  static volatile long x = 0;

  static Semaphore s = new Semaphore(1, true);

  public static void main(String... args) {
    Runnable task1 = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 100; i++) {
          System.out.println("task 1 writes ...");
          Threads.withSemaphore(s, new Runnable() {
            @Override
            public void run() {
              x += 2;
            }
          });
          System.out.println("task 1 goes to sleep...");
          Threads.sleep(rnd.nextInt(200));
        }
      }
    };

    Runnable task2 = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 100; i++) {
          System.out.println("task 2 writes ...");
          Threads.sleep(rnd.nextInt(80));
          Threads.withSemaphore(s, new Runnable() {
            @Override
            public void run() {
              x += 1;
            }
          });
          System.out.println("task 2 goes to sleep...");
        }
      }
    };

    System.out.println("Task main starts.");
    Thread t1 = new Thread(task1);
    Thread t2 = new Thread(task2);
    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(x);
    System.out.println("Task main has ended.");
  }

}
