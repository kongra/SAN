package bdia;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class Main {

  private static final HikariConfig config = new HikariConfig();
  private static final HikariDataSource ds;

  static {
    config.setJdbcUrl( "jdbc:postgresql://localhost/MAAS" );
    config.setUsername( "jee" );
    config.setPassword( "jee" );
    config.addDataSourceProperty( "cachePrepStmts" , "true" );
    config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
    config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
    ds = new HikariDataSource(config);
  }

  public static void main(String... args) throws SQLException {
    try (Connection conn = ds.getConnection()) {
      try (Statement stmt = conn.createStatement()) {
        try (ResultSet rs = stmt.executeQuery("select email from profiles")) {
          while (rs.next()) {
            System.out.println(rs.getString("email"));
          }
        }
      }
    }

    // ZMIENNE DYNAMICZNE (DYNAMICZNY ZAKRES ZMIENNYCH)

    System.out.println("It's ok.");
  }

}
