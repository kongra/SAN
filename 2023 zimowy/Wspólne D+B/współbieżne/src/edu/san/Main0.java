// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

public class Main0 {

  public static void main(String[] args) {
    final var t1 = new Thread(() -> {
      System.out.println("work2 goes to sleep");
      Threads.sleep(5_000);
      System.out.println("work2 ended");
    });
    t1.start();

    // Chcemy, aby t1 zakończył się przed końcem main

//    try {
//      // Wątek główny (Main) wywołuje t1.join. To powoduje, że Main zostaje
//      // wywłaszczony (zaparkowany) do momentu, gdy t1 zakończy działanie:
//      t1.join();
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }

    // Wątek główny (Main) wywołuje t1.join. To powoduje, że Main zostaje
    // wywłaszczony (zaparkowany) do momentu, gdy t1 zakończy działanie:
    Threads.run(t1::join);

    Threads.sleep(1_000);
    System.out.println("main t1.interrupt()");
    t1.interrupt();

    System.out.println("main ended");
  }

}
