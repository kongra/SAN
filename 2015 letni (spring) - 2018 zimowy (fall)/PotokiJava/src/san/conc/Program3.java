package san.conc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Program3 {

  private static long n = 0;

  private static Object monitor = new Object();

  private static final Semaphore s = new Semaphore(1, true);

  public static void main(String... args) {
    final List<Thread> threads = new ArrayList<>(1000);

    for (int i = 1; i <= 1000; i++) {
      Thread t = new Thread(() -> {
        try {
          s.acquire();
          s.acquire();

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        n += 1;
        s.release();
      });
      threads.add(t);
      t.start();
    }

    for (Thread t : threads) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println(n);
  }

}
