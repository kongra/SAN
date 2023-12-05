// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

public class Program10 {

  static class Holder<T> {

    private T value;

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
      holder1.set("aaa");
    });

    final var t2 = Threads.startNew(() -> {
      System.out.println(holder1.value());
    });

    Threads.run(t1::join);
    Threads.run(t2::join);
    System.out.println("Done");
  }

}
