// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

public class Program9 {

  public static void main(String[] args) {
    final var monitor1 = new Object();
    final var monitor2 = new Object();

    final var t1 = Threads.startNew(() -> {
      synchronized (monitor1) {
        Threads.sleep(100);
        synchronized (monitor2) {
          System.out.println("t1 has monitor1 and monitor2");
        }
      }
    });

    final var t2 = Threads.startNew(() -> {
      synchronized (monitor2) {
        Threads.sleep(200);
        synchronized (monitor1) {
          System.out.println("t2 has monitor2 and monitor1");
        }
      }
    });

    Threads.run(t1::join);
    Threads.run(t2::join);
    System.out.println("Done");

    // KOMENTARZE:
    // 1. Błędem jest wywoływanie w sekcji krytycznej "obcego" kodu!!!
    // 2. Generalnie: blokady się nie komponują!
    // 3. Aby stworzyć program, który nie ma deadlocków, trzeba "zapomnieć" o
    // zmiennych i o sekcjach krytycznych.
    // 4. Q: Co w zamian? A: Programowanie funkcyjne
  }

}
