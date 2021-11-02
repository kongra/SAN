package edu.san;

import java.util.Objects;

/**
 * Zadanie: Zrealizuj komponent Box, który naśladuje działanie skrytki
 * pocztowej: - Ludzie mogą wkładać (put) i wyciągać elementy (obiekty) ze
 * skrzynki - Gdy ktoś wkłada, ale Box jest pełny, osoba ta musi poczekać, aż
 * Box stanie się znowu pusty - Gdy ktoś wyciąga, ale Box jest pusty, osoba ta
 * musi poczekać, aż Box stanie się znowu pełny
 *
 * - Osobom odpowiadają wątki. - Box jest klasą, której obiekt(y) spełnia(ją)
 * powyższe wymagania
 *
 * @author kongra
 */
public class Program3 {

  static class Box {

    private Object putSentinel = new Object();
    private Object getSentinel = new Object();

    private String value;

    public boolean isEmpty() {
      return value == null;
    }

    public boolean isFull() {
      return !isEmpty();
    }

    public void put(String value) {
      Objects.requireNonNull(value);

      if (isFull()) {
        System.out
        .println(Thread.currentThread().getName() + " has to wait on put");
        Threads.wait(putSentinel);
      }

      this.value = value;

      System.out
      .println(Thread.currentThread().getName() + " succeeding with put");

      Threads.notify(getSentinel);
    }

    public String get() {
      if (isEmpty()) {
        System.out
        .println(Thread.currentThread().getName() + " has to wait on get");
        Threads.wait(getSentinel);
      }

      final String value = this.value;
      this.value = null;

      System.out
      .println(Thread.currentThread().getName() + " succeeding with get");

      Threads.notify(putSentinel);
      return value;
    }

  }

  public static void main(String... args) {
    Box b1 = new Box();

    Thread producer = new Thread(() -> {
      int i = 0;
      while (true) {
        i++;
        System.out.println(Thread.currentThread().getName() + " put...");
        b1.put("value" + i);
        System.out.println(Thread.currentThread().getName() + " put done.");
        Threads.sleep(1000);
      }
    });

    Thread consumer = new Thread(() -> {
      while (true) {
        System.out.println(Thread.currentThread().getName() + " get...");
        String s = b1.get();
        System.out
        .println(Thread.currentThread().getName() + " get done with " + s);
        Threads.sleep(2000);
      }
    });

    producer.start();
    consumer.start();
  }

}
