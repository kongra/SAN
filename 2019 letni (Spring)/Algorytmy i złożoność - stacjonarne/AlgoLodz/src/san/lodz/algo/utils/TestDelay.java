package san.lodz.algo.utils;

import san.lodz.math.Nullary;

import static san.lodz.algo.utils.Delay.delay;

public class TestDelay {

  public static void main(String... args) {
    Nullary<Integer> n = delay(() -> {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return 1 + 2;
    });

    System.out.println(n);
    System.out.println(n.call());
    System.out.println(n);
    System.out.println(n);
    System.out.println(n.call());
    System.out.println(n);
  }

}
