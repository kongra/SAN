import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static san.jee6.jdbc.JDBC.*;

public class Test {

  private static void login(Connection conn,
                            String email,
                            long msecs) throws SQLException {
    restartingSerial(Long.MAX_VALUE, () -> {
      withTx(conn, Connection.TRANSACTION_SERIALIZABLE, () -> {
        iterQuery(conn,
            "select * from profiles where email='" + email + "'",
            rs -> {
              System.out.println(rs.getString("email"));
              return null;
            });

        long n = execUpdate(conn,
            "update profiles set last_login = now() " +
                "where email = '" + email + "'");

        System.out.println("Changed " + n);

        try {
          System.out.println("Goto sleep.");
          Thread.sleep(msecs);
          System.out.println("COMMIT");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return null;
      });
      return null;
    });
  }

  public static void main(String... args) {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    try (Connection conn1 = DriverManager.getConnection(
        "jdbc:postgresql://localhost/JEE",
        "jee",
        "jee12345")) {
      try (Connection conn2 = DriverManager.getConnection(
          "jdbc:postgresql://localhost/JEE",
          "jee",
          "jee12345")) {

        Thread t1 = new Thread(() -> {
          try {
            login(conn1, "pangeon@tlen.pl", 1000);
          } catch (SQLException e) {
            e.printStackTrace();
          }
        });

        Thread t2 = new Thread(() -> {
          try {
            login(conn2, "pangeon@tlen.pl", 500);
          } catch (SQLException e) {
            e.printStackTrace();
          }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
      }

    } catch (SQLException e) {
      e.printStackTrace(System.err);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

}
