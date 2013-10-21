package san.concurrent;

public class Lecture01 {

  static boolean running = true;

  static Object monitor = new Object();

  public static boolean isRunning() {
    synchronized (monitor) {
      return running;
    }
  }

  public static void finish() {
    synchronized (monitor) {
      running = false;
    }
  }

  public static void main(String[] args) {
    // test1();
    // test2();
    // test3();

    Runnable daemon = new Runnable() {
      @Override
      public void run() {
        while (isRunning()) {
          System.out
              .println("Działa wątek " + Thread.currentThread().getName());

          Th.sleep(1000L);
        }
      }
    };

    Th.start(daemon);

    System.out.println("Działa wątek " + Thread.currentThread().getName());
    Th.sleep(5000L);
    finish();
  }

  private static void test3() {
    Runnable workA = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          System.out.println("Działa A");
          Th.sleep(100);
        }
      }
    };

    Runnable workB = new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 20; i++) {
          System.out.println("Działa B");
          Th.sleep(50);
        }
      }
    };

    Th.start(1000, workA);
    Th.start(1000, workB);
  }

  private static void test2() {
    Thread t1 = Th.start(new Runnable() {
      @Override
      public void run() {
        System.out.println("Wykonuję pracę!!!");
        Th.sleep(2000L);
        System.out.println("Praca wykonana!!!");
      }
    });

    Th.join(t1);

    System.out.println("Koniec.");
  }

  private static void test1() {
    Runnable work = new Runnable() {
      @Override
      public void run() {
        System.out.println("Wykonuję pracę!!!");
        Th.sleep(2000L);
        System.out.println("Praca wykonana!!!");
      }
    };

    Thread t1 = new Thread(work);
    t1.start();

    Th.join(t1);

    System.out.println("Hello World!!!");
  }

}
