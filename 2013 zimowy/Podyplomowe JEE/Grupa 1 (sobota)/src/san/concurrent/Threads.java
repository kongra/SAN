package san.concurrent;

import java.util.Random;

public class Threads {

  public static void println(String msg) {
    System.out.println(Thread.currentThread().getName() + " " + msg);
  }

  public static void println(String format, Object... args) {
    println(String.format(format, args));
  }

  public static boolean wait(Object obj) {
    try {
      obj.wait();
      return true;
    }
    catch (InterruptedException e) {
      return false;
    }
  }

  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void sleepRandomly(int maxMillis) {
    try {
      Thread.sleep(random.nextInt(maxMillis + 1));
    }
    catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void future(String familyName, Runnable... tasks) {
    int count = 1;
    for (Runnable t : tasks) {
      new Thread(t, familyName + "-" + count).start();
      count += 1;
    }
  }

  public static void futures(int n, String familyName, Runnable... tasks) {
    for (int i = 0; i < n; i++) {
      future(familyName + "-" + (i + 1), tasks);
    }
  }

  private static final Random random = new Random();

  private Threads() {
    ;
  }

}
