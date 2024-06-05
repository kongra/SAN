// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.security.SecureRandom;
import java.util.Random;

import telsos.Delay;

interface Program11 {

  static int veeeeryLongComputations() {
    try {
      System.out.println("Computing...");
      Thread.sleep(4000);
      System.out.println("Done!");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return 5;
  }

  // final int n = veeeeryLongComputations();
  // Byłoby dobrze, gdyby n było liczone dopiero wtedy, kiedy jest to potrzebne.

  final Delay<Integer> m = Delay.of(Program11::veeeeryLongComputations);

  static void main(String... args) {
    Random r = new SecureRandom();
    var flag = r.nextBoolean();
    if (flag) {
      System.out.println("main::" + m.get());
      System.out.println("main::" + m.get());
    } else {
      System.out.println("main::obliczenie nie było potrzebne");
    }
  }

}
