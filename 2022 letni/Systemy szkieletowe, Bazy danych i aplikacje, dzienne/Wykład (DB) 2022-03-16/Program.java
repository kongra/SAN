import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Program {

  public static void main(String... args) {
    test2();
  }

  private static void test2() {
    var url = "jdbc:postgresql://localhost/JEE1";
    var props = new Properties();
    var passwd = System.getProperty("jdbc.password");
    props.setProperty("user", "jee1");
    props.setProperty("password", passwd);

    try (var conn = DriverManager.getConnection(url, props)) {
      try (var stmt = conn.createStatement()) {
        try (var rs = stmt.executeQuery("select * from test1")) {
          while (rs.next()) {
            var id = rs.getLong("id");
            var firstName = rs.getString("first_name");

            System.out.println(id + ", " + firstName);
          }
        }

        stmt.executeUpdate(
            "insert into test1(first_name) values (" + "'Tomasz'" + ")");
      }
    } catch (SQLException e) {
      e.printStackTrace(System.err);
    }
  }

  private static void test1() {
    var url = "jdbc:postgresql://localhost/JEE1";
    var props = new Properties();
    var passwd = System.getProperty("jdbc.password");
    props.setProperty("user", "jee1");
    props.setProperty("password", passwd);

    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url, props);
      System.out.println("Ustanowiłem połączenie " + conn);
    } catch (SQLException e) {
      e.printStackTrace(System.err);
    } finally {
      try {
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace(System.err);
      }
    }
  }

}
