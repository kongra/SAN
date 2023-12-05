// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.concurrent.Semaphore;

public class Program8 {

  public static void main(String[] args) {
    final var s1 = new Semaphore(1, true);
    final var s2 = new Semaphore(1, true);

    final var t1 = Threads.startNew(() -> {
      Threads.acquiring(s1, () -> {
        Threads.sleep(100);
        Threads.acquiring(s2, () -> {
          System.out.println("t1 has s1 and s2");
        });
      });
    });

    final var t2 = Threads.startNew(() -> {
      Threads.acquiring(s2, () -> {
        Threads.sleep(200);
        Threads.acquiring(s1, () -> {
          System.out.println("t2 has s2 and s1");
        });
      });
    });

    Threads.run(t1::join);
    Threads.run(t2::join);
  }

}
