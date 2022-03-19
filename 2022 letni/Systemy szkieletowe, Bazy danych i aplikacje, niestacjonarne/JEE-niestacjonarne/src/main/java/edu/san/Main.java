package edu.san;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {

  public static void main(String... args) {
    var url = "jdbc:postgresql://localhost:5432/JEE2";
    var props = new Properties();
    var passwd = System.getProperty("jee2.password");

    props.setProperty("user", "jee2");
    props.setProperty("password", passwd);

    test2(url, props);
  }

  private static void test2(String url, Properties props) {
    try (var conn = DriverManager.getConnection(url, props);
        var stmt = conn.createStatement();
        var rs = stmt.executeQuery("select id, email from test1")) {

      while (rs.next()) {
        long id = rs.getLong("id");
        String email = rs.getString("email");
        System.out.println(id + ", " + email);
      }

      stmt.executeUpdate("insert into test1(email) values('some@email.com')");

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
