package san.jee.lect07;

import san.coll.fn.Delay;
import san.coll.fn.NoArg;

public class TestDelay {

  static Delay d;

  public static void main(String[] args) {
    d = Delay.create(new NoArg<Object>() {
      @Override
      public Object call() {
        new Thread(new Runnable() {
          @Override
          public void run() {
            d.call();
          }

        }).start();
        return null;
      }
    });

  }

}
