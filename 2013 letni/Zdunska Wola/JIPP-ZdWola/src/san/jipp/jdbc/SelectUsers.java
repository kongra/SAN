package san.jipp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectUsers {

  static {
    try {
      Class.forName("org.postgresql.Driver");
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      conn =
          DriverManager.getConnection("jdbc:postgresql://localhost/MAAS",
            "scott", "tiger");

      stmt = conn.createStatement();
      rs = stmt.executeQuery("select * from users");

      while (rs.next()) {
        System.out.println(rs.getLong("id") + " " + rs.getString("login"));
      }

      stmt
          .executeUpdate("insert into users(login, passwd) values('test', 'test')");
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    finally {
      if (rs != null) {
        try {
          rs.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (stmt != null) {
        try {
          stmt.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (conn != null) {
        try {
          conn.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

}
