// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

public class Program5 {

  public static void main(String[] args) {
    System.out.println("main starts");

    final var obj = new Object();

    final var t1 = new Thread(() -> {
      System.out.println("t1 starts");
      synchronized (obj) {
        System.out.println("t1 calls obj.wait()");
        Threads.run(obj::wait);
      }
      System.out.println("t1 ends");
    });
    // t1.setDaemon(true);
    t1.start();

    Threads.sleep(1000);

    synchronized (obj) {
      System.out.println("main calls obj.notify()");
      obj.notify();
    }

    Threads.run(t1::join);
    System.out.println("main ends");
  }

}
