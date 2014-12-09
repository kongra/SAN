package san.conc.rw;

import java.util.Random;

public class Time {

  private static final Random random = new Random();

  public static void sleepAtMost(int millis) {
    try {
      Thread.sleep(random.nextInt(millis));
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
