package edu.san;

public class Program1 {

  static class Work implements Runnable {
    @Override
    public void run() {
      System.out.println("Działa Work...");
    }
  }

  public static void main(String... args) {
    Runnable w1 = new Work();
    Thread t1 = new Thread(w1);
    t1.start();

    Thread t2 = new Thread(() -> {
      for (;;) {
        System.out.println("Działa t2...");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          System.out.println("Przerwano mój słodki sen!");
        }
      }
    });
    t2.start();

    Thread t3 = new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(1800);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Budzik jestem :)");
        t2.interrupt();
      }
    });
    t3.start();

    System.out.println("t1 is " + t1.toString());
    System.out.println("main is done");
  }

}
