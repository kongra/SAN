package bdia;

import java.sql.*;

public class Main {

  static {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static void main(String... args) throws SQLException {
    try (Connection conn = DriverManager.getConnection(
        "jdbc:postgresql://localhost/MAAS", "jee", "jee")) {
      try (Statement stmt = conn.createStatement()) {
        try (ResultSet rs = stmt.executeQuery("select * from profiles")) {
          while (rs.next()) {
            System.out.println(rs.getString("email"));
          }
        }
      }
    }

    System.out.println("It's ok.");
  }

}
