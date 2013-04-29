package esklep.profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.codec.digest.DigestUtils;

public class Profile {

  static {
    try {
      Class.forName("org.postgresql.Driver");
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * @param login
   * @param passwd
   * @return identyfikator użytkownika poprawnie zweryfikowanego przy użyciu
   *         podanych parametrów, null jeśli uwierzytelnienie się nie powiedzie
   */
  public static Long authenticate(String login, String passwd) {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      conn =
          DriverManager.getConnection("jdbc:postgresql://localhost/MAAS",
            "scott", "tiger");
      stmt = conn.createStatement();
      rs =
          stmt.executeQuery("select id, passwd from users where login='"
              + login + "'");

      if (!rs.next()) {
        return null;
      }

      long id = rs.getLong("id");
      String md5passwd = rs.getString("passwd");

      return md5passwd.equals(DigestUtils.md5Hex(passwd)) ? id : null;
    }
    catch (Exception e) {
      return null;
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
