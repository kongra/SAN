package bdia;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.Supplier;

public class DBI {

  private static final DynVar<Connection> conn = DynVar.create();

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

  @Nullable
  public static void withConn(Runnable body)
      throws SQLException {
    try (var c = ds.getConnection()) {
      conn.binding(c, body);
    }
  }

  public static Connection currentConn() {
    return conn.value();
  }

  public static void restartingTx(Runnable body) {
    while(true) {
      try {
        body.run();
        return;
      } catch (Throwable e) {
        if (!isConcurrentUpdateException(e)) {
          if (e instanceof SQLException) {
            throw e;
          }
          throw new RuntimeException(e);
        }
      }
    }
  }

  private static boolean isConcurrentUpdateException(Throwable e) {
    if (e instanceof SQLException) {
      var sqlState = ((SQLException) e).getSQLState();
      System.out.println("SQL state=" + sqlState);
      return "40001".equals(sqlState);
    }
    return false;
  }

  @Nullable
  public static void withNewTransaction(int isolationLevel, Runnable body) {
    Connection c = conn.value();
    try {
      final boolean autoCommit = c.getAutoCommit();
      try {
        System.out.println("Start transakcji w wątku" + Thread.currentThread());
        c.setAutoCommit(false);
        c.setTransactionIsolation(isolationLevel);

        body.run();
        System.out.println("Commit transakcji w wątku" + Thread.currentThread());
        c.commit();
      } catch (Throwable e) {
        c.rollback();
        throw e;
      } finally {
        c.setAutoCommit(autoCommit);
      }
    }
    catch(Throwable t) {
      throw new RuntimeException(t);
    }
  }

  @Nullable
  public static <T> T executingQuery(String query
      , @NotNull Function<ResultSet, T> body) {
    Connection c = conn.value();
    try (var stmt = c.createStatement()) {
      try (var rs = stmt.executeQuery(query)) {
        return body.apply(rs);
      }
    }
    catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  @Nullable
  public static <T> T execQuery1(String query, String columnLabel) {
    Connection c = conn.value();
    try (var stmt = c.createStatement()) {
      try (var rs = stmt.executeQuery(query)) {
        if (rs.next()) {
          return (T) rs.getObject(columnLabel);
        }
        return null;
      }
    }
    catch (Throwable t) {
      throw new RuntimeException(t);
    }
  }

  public static int execUpdate(String query, @NotNull Object... args) {
    Connection c = conn.value();
    try (var stmt = c.prepareStatement(query)) {
      for (int i = 0; i < args.length; i++) {
        stmt.setObject(i+1, args[i]);
      }
      return stmt.executeUpdate();
    }
    catch(Throwable e) {
      throw new RuntimeException(e);
    }
  }

}
