package bdia;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBI {

  static abstract class Transactor implements Runnable {

    final long sleepMsecs1;

    final long sleepMsecs2;

    final long amountDelta;

    Transactor(long sleepMsecs1, long sleepMsecs2, long amountDelta) {
      this.sleepMsecs1 = sleepMsecs1;
      this.sleepMsecs2 = sleepMsecs2;
      this.amountDelta = amountDelta;
    }

    @Override
    public void run() {
      sleep(sleepMsecs1);
      System.out.println(Thread.currentThread() + "after sleep1");
      try {
        DBI.serializableWithConn(() -> {
          sleep(sleepMsecs1);
          System.out.println(Thread.currentThread() + "after sleep2");
          runSQL();
          sleep(sleepMsecs2);
          System.out.println(Thread.currentThread() + "after sleep3");
        });
      } catch (SQLException t) {
        t.printStackTrace();
      }
    }

    protected abstract void runSQL();

    private void sleep(long msecs) {
      try {
        Thread.sleep(msecs);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  static class Ref<T> {

    public T value;

    public Ref() {
    }

    public Ref(T value) {
      this.value = value;
    }
  }

  public static void main(String... args) throws InterruptedException {
    final Ref<Long> amountValue = new Ref<>();
    Thread t1 = new Thread(new Transactor(
        1000, 1000, 100) {
      @Override
      protected void runSQL() {
//        Long amount = DBI.execQuery1("select amount from profiles where id=1",
//            "amount");
//        System.out.println(amount);
//
//        amountValue.value = amount;
        var query = "update profiles set amount = amount - " + amountDelta +
            " where id = 1";
        DBI.execUpdate(query);
      }
    });

    Thread t2 = new Thread(new Transactor(
        1200, 300, 50) {
      @Override
      protected void runSQL() {
        var query = "update profiles set amount = amount - " + amountDelta +
            " where id = 1";
        DBI.execUpdate(query);
      }
    });

    System.out.println("Start");
    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println("Done with " + amountValue.value);
  }

}
