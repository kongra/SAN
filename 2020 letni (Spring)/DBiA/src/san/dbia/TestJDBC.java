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
    new Thread(() -> test3()).start();
    new Thread(() -> test4()).start();
  }

  private static void test3() {
    DBI.withNewTransaction(Connection.TRANSACTION_SERIALIZABLE
        , conn -> {
          for (int i = 0; i < 5; i++) {
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
//            System.out.println("test3 -> insert");
//            DBI.execUpdate(conn
//                , "insert into profiles(first_name, last_name, amount) values(?, ?, ?) "
//                , "Adam", "Nowak", 267);

            System.out.println("test3 -> update");
            DBI.execUpdate(conn
                , "update profiles set amount = amount + 20 where id=50");
          }

          System.out.println("test3 -> Idziemy spać przed zatwierdzeniem");
          try {
            Thread.sleep(10000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          return null;
        });
  }

  private static void test4() {
    DBI.withNewTransaction(Connection.TRANSACTION_SERIALIZABLE
        , conn -> {
          for (int i = 0; i < 4; i++) {
            try {
              Thread.sleep(300);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
//            System.out.println("test4 -> insert");
//            DBI.execUpdate(conn
//                , "insert into profiles(first_name, last_name, amount) values(?, ?, ?) "
//                , "Jan", "Nowacki", 256);

            System.out.println("test4 -> update");
            DBI.execUpdate(conn
                , "update profiles set amount = amount + 30 where id=50");
          }

          System.out.println("test4 -> Idziemy spać przed zatwierdzeniem");
          try {
            Thread.sleep(10000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          return null;
        });
  }

  private static void test2() {
    Long amount = DBI.withNewTransaction(Connection.TRANSACTION_SERIALIZABLE
        , conn -> DBI.execQuery1(conn
        , "select amount from profiles where id=4"
        , "amount"));

    System.out.println("I have amount of " + amount);
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
