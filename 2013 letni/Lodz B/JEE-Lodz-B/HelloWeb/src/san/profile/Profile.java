package san.profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.codec.digest.DigestUtils;

import san.jdbc.JDBC;
import san.util.Body;
import san.util.Body.Break;
import san.util.Body.Continue;
import san.util.Doclean;
import san.util.StringUtils;
import san.util.refs.Ref;

public class Profile {

  public static final String UID = "u23r1d";

  static {
    try {
      Class.forName("org.postgresql.Driver");
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static Long validate(final String login, final String passwd) {
    if (StringUtils.isBlank(login) || StringUtils.isBlank(passwd)) {
      return null;
    }

    final Ref<Long> id = Ref.initially(null);
    final Ref<String> p = Ref.initially(null);

    Doclean.run(new Runnable() {
      @Override
      public void run() {
        JDBC.withConnection(JDBC.pool(), new Runnable() {
          @Override
          public void run() {
            JDBC.withQueryResults("select id, passwd from users where login='"
                + login + "'", new Body() {
              @Override
              public void run() throws Break, Continue {
                id.value = JDBC.get("id");
                p.value = JDBC.get("passwd");
              }
            });
          }
        });
      }
    });

    if(id.value == null) {
      return null;
    }
    if (DigestUtils.md5Hex(passwd).equals(p.value)) {
      return id.value;
    }
    return null;

//    Connection conn = null;
//    Statement stmt = null;
//    ResultSet rs = null;
//    try {
//      conn =
//          DriverManager.getConnection("jdbc:postgresql://localhost/MAAS",
//            "scott", "tiger");
//      stmt = conn.createStatement();
//      rs =
//          stmt.executeQuery("select id, passwd from users where login='"
//              + login + "'");
//
//      if (!rs.next()) {
//        return null;
//      }
//
//      String p = rs.getString("passwd");
//      if (DigestUtils.md5Hex(passwd).equals(p)) {
//        return Long.valueOf(rs.getLong("id"));
//      }
//      return null;
//    }
//    catch (SQLException e) {
//      e.printStackTrace();
//      return null;
//    }
//    finally {
//      if (rs != null) {
//        try {
//          rs.close();
//        }
//        catch (SQLException e) {
//          e.printStackTrace();
//        }
//      }
//
//      if (stmt != null) {
//        try {
//          stmt.close();
//        }
//        catch (SQLException e) {
//          e.printStackTrace();
//        }
//      }
//
//      if (conn != null) {
//        try {
//          conn.close();
//        }
//        catch (SQLException e) {
//          e.printStackTrace();
//        }
//      }
//    }
  }

  private Profile() {
    ;
  }

}
