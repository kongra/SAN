package edu.san.conc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class DelayExample {

  public static void main(String... args) {
    final var t1 = new AtomicReference<Thread>();
    final var t2 = new AtomicReference<Thread>();

    final var dr2 = new AtomicReference<Delay<String>>();
    Delay<String> d1 = Delay.of(() -> {
      System.out.println("Evaluating d1 with d2 value");
      dr2.get().value();
      return "www";
    });

    final var dr1 = new AtomicReference<Delay<String>>();
    Delay<String> d2 = Delay.of(() -> {
      System.out.println("Evaluating d2 with d1 value");
      dr1.get().value();
      return "aaa";
    });

    t1.set(new Thread(() -> {
      System.out.println(d1.isMaterialized());
      System.out.println(d1.value());
      System.out.println(d1.isMaterialized());
    }));

    t2.set(new Thread(() -> {
      System.out.println(d2.isMaterialized());
      System.out.println(d2.value());
      System.out.println(d2.isMaterialized());
    }));

    dr1.set(d1);
    dr2.set(d2);

    t1.get().start();
    t2.get().start();

    Threads.sleep(3L, TimeUnit.DAYS);

  }

}
