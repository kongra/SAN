// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.function.Supplier;

public class Program12 {

  static class Holder<T> {

    private T value;

    void set(Supplier<T> supplier) {
      final var v = supplier.get();
      set(v);
    }

    synchronized void set(T value) {
      this.value = value;
    }

    synchronized T value() {
      return this.value;
    }

    Holder(T value) {
      this.value = value;
    }

  }

  public static void main(String[] args) {
    final var holder1 = new Holder<String>(null);

    final var t1 = Threads.startNew(() -> {
      holder1.set(() -> {
        final var t2 = Threads.startNew(() -> {
          System.out.println(holder1.value());
        });
        Threads.run(t2::join);
        return "bbb";
      });
    });

    // Do domu: Proszę poczytać sobie o znaczeniu słów:
    // async, await
    // w języku JavaScript!
    
    Threads.run(t1::join);
    System.out.println("Done");
  }

}
