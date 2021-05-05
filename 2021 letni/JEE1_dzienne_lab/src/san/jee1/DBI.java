package san.jee1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBI {

  private static HikariConfig config = new HikariConfig();
  private static HikariDataSource ds;

  static {
    try {
      Class.forName("org.postgresql.Driver");

      config.setJdbcUrl("jdbc:postgresql://localhost/MAAS");
      config.setUsername("jee");
      config.setPassword("jee");
      config.addDataSourceProperty("cachePrepStmts", "true");
      config.addDataSourceProperty("prepStmtCacheSize", "250");
      config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
      ds = new HikariDataSource(config);

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

  private DBI() {
  }

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }

  public static <T> T selectOne(String query, String name) {
    return selectOne(ds, query, name);
  }

  public static <T> T selectOne(DataSource ds, String query, String name) {
    try (Connection conn = ds.getConnection()) {
      return selectOne(conn, query, name);
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> T selectOne(Connection conn, String query, String name) {
    try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query)) {
      if (rs.next()) {
        return (T) rs.getObject(name);
      }
      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

}
