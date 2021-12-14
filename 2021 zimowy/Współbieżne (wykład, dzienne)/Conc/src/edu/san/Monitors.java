package edu.san;

public class Monitors {

  static class Accounts {
    private long account1 = 10_000;
    private long account2 = 10_000;

    synchronized void modify(int amount) {
      account1 += amount;
      account2 -= amount;
    }

    // void modify(int amount) {
    // synchronized (this) {
    // account1 += amount;
    // account2 -= amount;
    // }
    // }

    @Override
    public synchronized String toString() {
      return "account1: " + account1 + ", account2: " + account2 + ", sum is "
          + (account1 + account2);
    }

  }

  public static void main(String... strings) {
    // test1();

    Accounts accs = new Accounts();

    var t1 = new Thread(() -> {
      for (int i = 0; i < 10_000_000; i++) {
        accs.modify(10);
      }

    });

    var t2 = new Thread(() -> {
      for (int i = 0; i < 10_000_000; i++) {
        accs.modify(-10);
      }
    });

    t1.start();
    t2.start();

    Threads.join(t1, t2);

    System.out.println(accs);
  }

  @SuppressWarnings("unused")
  private static void test1() {
    var m1 = new Object();

    var t1 = new Thread(() -> {
      synchronized (m1) {
        System.out.println("Train 1: JESTEM W SEKCJI KRYTYCZNEJ");
        Threads.sleep(2000);

        synchronized (m1) {
          System.out.println("Train 1: JEST OK");
        }

        System.out.println("Train 1: OPUSZCZAM SEKCJĘ KRYTYCZNĄ");
      }
    });

    var t2 = new Thread(() -> {
      synchronized (m1) {
        System.out.println("Train 2: JESTEM W SEKCJI KRYTYCZNEJ");
        Threads.sleep(1000);
        System.out.println("Train 2: OPUSZCZAM SEKCJĘ KRYTYCZNĄ");
      }
    });

    t1.start();
    t2.start();
  }

}
