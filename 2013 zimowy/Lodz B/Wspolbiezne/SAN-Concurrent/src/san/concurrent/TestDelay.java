package san.concurrent;

import san.concurrent.utils.Delay;
import san.fn.Nullary;

public class TestDelay {

  public static int bardzoDługieObliczenia(int n) {
    System.out.println("Rozpoczynam obliczenia");
    sleep(3000);
    System.out.println("Obliczenia zakończone");
    return n + 3;
  }

  private static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Delay<Integer> wartość = Delay.create(new Nullary<Integer>() {
      @Override
      public Integer call() {
        return bardzoDługieObliczenia(5);
      }
    });

    System.out.println(wartość.deref());
  }

}
