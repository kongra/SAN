package san.concurrent;

import java.util.ArrayList;
import java.util.List;

public class Th {

  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static Thread join(Thread t) {
    try {
      t.join();
      return t;
    }
    catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static Thread start(Runnable body) {
    Thread t = new Thread(body);
    t.start();
    return t;
  }

  public static List<Thread> start(int n, Runnable body) {
    if (n < 0) {
      throw new IllegalArgumentException("Illegal n : " + n);
    }

    List<Thread> result = new ArrayList<Thread>(n);
    for (int i = 0; i < n; i++) {
      result.add(new Thread(body));
    }

    for (Thread thread : result) {
      thread.start();
    }

    return result;
  }

}
