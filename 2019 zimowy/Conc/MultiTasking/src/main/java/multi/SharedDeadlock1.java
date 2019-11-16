package multi;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Semaphore;

public class SharedDeadlock1 {

  private static Semaphore s1 = new Semaphore(1, true);

  private static int count = 0;

  static String myTrain;

  static void safelyAcquiring(
      @NotNull final Semaphore s,
      final int permits,
      @NotNull final Runnable body) {
    boolean acquired = false;
    try {
      s.acquire(permits);
      acquired = true;
      body.run();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      if (acquired)
        s.release(permits);
    }
  }

  @NotNull
  @Contract("_ -> new")
  static Thread makeTrain(String s) {
    return new Thread(() -> {
      safelyAcquiring(s1, 1, () -> {
        safelyAcquiring(s1, 1, () -> {
          count += s.length();
          System.out.println("Obecnie count jest równy " + count);
        });
      });
    });
  }

  public static void main(String... args) {
    makeTrain("E-09").start();
    makeTrain(myTrain).start();
    makeTrain("E-08").start();

    // Semafory nie są RE-ENTRANT

    System.out.println("Koniec.");
  }

}
