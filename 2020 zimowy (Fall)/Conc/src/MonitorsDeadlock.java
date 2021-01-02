// Copyright (c) Konrad Grzanek
// Created 02.01.2021

public class MonitorsDeadlock {

  // PRZEPIS NA KATASTROFĘ:
  // * Kiedy w bloku synchronized(m1) próbujemy synchronized(m2)
  // * Ale hermetyzacja stosowana prawidłowo nie pozwoli na wykonanie
  //   powyższego działania
  // * Więc należy stosować następujący przepis:
  // * Pozwalamy na wywołanie w bloku synchronized(m1) jakiegoś obcego kodu
  //   (foreign code)!!!
  // OBCY KOD: kod napisany przez kogoś innego, nad którym to kodem nie mamy
  //           żadnej kontroli (może on robić cokolwiek).

  // PRZEPIS NA BRAK ZAKLESZCZEŃ:
  // * NIGDY NIE WOLNO w bloku synchronized() ani w żadnej innej sekcji krytycznej
  //   wywoływać OBCEGO KODU!!!
  // * NIGDY NIE WOLNO będąc w sekcji krytycznej WCHODZIĆ DO INNEJ sekcji krytycznej!!!

  public static void main(String... args) {
    Object m1 = new Object();
    Object m2 = new Object();

    Thread t1 = new Thread(() -> {
      synchronized (m1) {
        ThreadTools.sleep(1000);
        synchronized (m2) {
        }
        System.out.println("t1 finished");
      }
    });

    Thread t2 = new Thread(() -> {
      synchronized (m2) {
        ThreadTools.sleep(1000);
        synchronized (m1) {
        }
        System.out.println("t2 finished");
      }
    });

    t1.start();
    t2.start();

    System.out.println("main finished");
  }

}
