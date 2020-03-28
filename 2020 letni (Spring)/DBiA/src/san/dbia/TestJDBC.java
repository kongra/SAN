package san.dbia;
import java.sql.*;
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
    test2();
  }

  private static void test2() {
    Long amount = DBI.withConn(conn -> {
      return DBI.executingQuery(conn, "select amount from profiles where id=4", rs -> {
        try {
          return rs.getLong("amount");
        } catch (SQLException e) {
          e.printStackTrace();
          return null;
        }
      });
    });

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
