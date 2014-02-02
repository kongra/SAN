package san.lect06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.codec.digest.DigestUtils;

public class User {

  static {
    try {
      Class.forName("org.postgresql.Driver");
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static Long addUser(String login, String passwd) {
    String QUERY =
        "insert into users(login, passwd) values('" + login + "', '"
            + DigestUtils.md2Hex(passwd) + "')";

    try (Connection conn =
        DriverManager.getConnection("jdbc:postgresql://localhost/JEE2", "jee2",
          "jee2")) {

      // BEGIN;
      conn.setAutoCommit(false);
      conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

      try (Statement stmt = conn.createStatement()) {
        stmt.executeUpdate(QUERY, Statement.RETURN_GENERATED_KEYS);
        try (ResultSet rs = stmt.getGeneratedKeys()) {
          rs.next();
          long id = rs.getLong(1);
          
          // COMMIT;
          conn.commit();

          return id;
        }
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Long validUserID(String login, String passwd) {
    String QUERY = "select id, passwd from users where login='" + login + "'";

    try (Connection conn =
        DriverManager.getConnection("jdbc:postgresql://localhost/JEE2", "jee2",
          "jee2")) {
      try (Statement stmt = conn.createStatement()) {
        try (ResultSet rs = stmt.executeQuery(QUERY)) {
          if (rs.next()) {
            String p = rs.getString("passwd");
            if (DigestUtils.md5Hex(passwd).equals(p)) {
              return rs.getLong("id");
            }
            return null;
          }
          return null;
        }
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Long validUserID2(String login, String passwd) {
    String QUERY = "select id, passwd from users where login='" + login + "'";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
      conn =
          DriverManager.getConnection("jdbc:postgresql://localhost/JEE2",
            "jee2", "jee2");

      stmt = conn.createStatement();
      rs = stmt.executeQuery(QUERY);

      if (rs.next()) {
        String p = rs.getString("passwd");
        if (DigestUtils.md5Hex(passwd).equals(p)) {
          return rs.getLong("id");
        }
        return null;
      }
    }
    catch (Exception e) {

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
    return null;
  }

  private User() {
    ;
  }

}
