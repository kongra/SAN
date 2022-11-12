package edu.san.conc;

import java.util.concurrent.atomic.AtomicReference;

public class DelayExample {

  public static void main(String... args) {
    final var t1 = new AtomicReference<Thread>();
    final var t2 = new AtomicReference<Thread>();
    
    Delay<String> d1 = Delay.of(() -> {
      System.out.println("Evaluating d1");
      Threads.join(t2.get());
      return "www";
    });
    
    Delay<String> d2 = Delay.of(() -> {
      System.out.println("Evaluating d2");
      Threads.join(t1.get());
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
    
    t1.get().start();
    t2.get().start();

  }

}
