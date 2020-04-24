package san.dbia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestJDBC {

  static {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void main(String... args) {
    // test1();
    // test2();
    new Thread(() -> {
      try {
        test3();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        test4();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }).start();
  }

  private static void test3() throws SQLException {
    DBI.withConn(() -> {
      DBI.restartingTx(() -> {
        DBI.withNewTransaction(Connection.TRANSACTION_SERIALIZABLE, () -> {
          for (int i = 0; i < 5; i++) {
            threadSleep(500);
            System.out.println("test3 -> update");
            DBI.execUpdate("update profiles set amount = amount + 20 where id=50");
          }
          System.out.println("test3 -> Idziemy spać przed zatwierdzeniem");
          threadSleep(10000);
        });
      });
    });
  }

  private static void test4() throws SQLException {
    DBI.withConn(() -> {
      DBI.restartingTx(() -> {
        DBI.withNewTransaction(Connection.TRANSACTION_SERIALIZABLE, () -> {
          for (int i = 0; i < 4; i++) {
            threadSleep(300);
            System.out.println("test4 -> update");
            DBI.execUpdate("update profiles set amount = amount + 30 where id=50");
          }
          System.out.println("test4 -> Idziemy spać przed zatwierdzeniem");
          threadSleep(10000);
        });
      });
    });
  }

  private static void threadSleep(int msecs) {
    try {
      Thread.sleep(msecs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void test1() {
    Properties props = new Properties();
    props.setProperty("user","maas");
    props.setProperty("password","maas12345");
    props.setProperty("ssl","false");
    try (var conn = DriverManager.getConnection(
        "jdbc:postgresql://localhost/MAAS"
        , props)) {

      try (var stmt = conn.createStatement()) {
        try (var rs = stmt.executeQuery("select * from profiles")) {
          while (rs.next()) {
            var id = rs.getLong("id");
            var firstName = rs.getString("first_name");
            var email = rs.getString("email");
            var amount = rs.getLong("amount");

            System.out.println("id=" + id +
                ", firstName=" + firstName +
                ", email=" + email +
                ", amount=" + amount);
          }
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    System.out.println("Done.");
  }

}
