// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.function.Supplier;

public class Program11 {

  static class Holder<T> {

    private T value;

    synchronized void set(Supplier<T> supplier) {
      value = supplier.get();

      // Wywołanie supplier.get() jest wywołaniem "obcego" kodu, tj. kodu,
      // którego działania nie możemy przewidzieć, ponieważ pozostaje on poza
      // zakresem naszej kontroli, jest napisany przez kogoś innego.
    }

    synchronized T value() {
      return value;
    }

    Holder(T value) {
      this.value = value;
    }

  }

  public static void main(String[] args) {
    final var holder1 = new Holder<String>(null);

    final var t1 = Threads.startNew(() -> holder1.set(() -> {
      final var t2 = Threads
          .startNew(() -> System.out.println(holder1.value()));
      Threads.run(t2::join);
      return "bbb";
    }));

    Threads.run(t1::join);
    System.out.println("Done");
  }

}
