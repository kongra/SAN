package wawa.db;

import java.sql.*;

public class Test {

  public static void main(String... args) {
    try {
      try(Connection conn = Pool.getConnection()) {
        try (Statement s = conn.createStatement()) {
          try (ResultSet rs = s.executeQuery(
              "select id, creation_stamp from profiles")) {

            while(rs.next()) {
              long id = rs.getLong("id");
              Timestamp cs = rs.getTimestamp("creation_stamp");

              System.out.println(id + ", " + cs);
            }
          }
        }
      }

    } catch (Throwable e) {
      e.printStackTrace(System.err);
    }
  }

}
