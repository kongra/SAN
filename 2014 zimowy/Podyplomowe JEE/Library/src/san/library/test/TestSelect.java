package san.library.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {

  public static void main(String[] args) {
    initDriver();
    try (Connection conn =
        DriverManager.getConnection("jdbc:postgresql://localhost/JEE3", "jee3",
          "jee3")) {
      try (Statement stmt = conn.createStatement()) {
        try (ResultSet rs =
            stmt.executeQuery("select r.id as rid, b.id as bid, u.id as uid "
                + "from reservations as r, users as u, books as b "
                + "where r.bookid = b.id " + "and r.userid = u.id")) {
          
          while(rs.next()) {
            System.out.println(rs.getLong("bid"));
          }
        }
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private static void initDriver() {
    try {
      Class.forName("org.postgresql.Driver");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
