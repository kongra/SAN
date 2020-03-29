package san.dbia;

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

  private static final Properties props = new Properties();

  static {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    props.setProperty("user","maas");
    props.setProperty("password","maas12345");
    props.setProperty("ssl","false");
  }

  // 1. Pule połączeń
  private static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(
        "jdbc:postgresql://localhost/MAAS"
        , props);
  }

  // 2. Wykonywanie operacji z wykorzystaniem Connection
  @Nullable
  public static <T> T withConn(@NotNull Function<Connection, T> body) {
    try (var conn = getConnection()) {
      return body.apply(conn);
    } catch (SQLException e) {
      e.printStackTrace(System.err);
      return null;
      // throw new RuntimeException(e);
    }
  }

  @Nullable
  public static <T> T withNewTransaction(int isolationLevel
      , @NotNull Function<Connection, T> body) {

    try (var conn = getConnection()) {
      final boolean autoCommit = conn.getAutoCommit();
      try {
        System.out.println("Start transakcji w wątku" + Thread.currentThread());
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(isolationLevel);

        var result = body.apply(conn);
        System.out.println("Commit transakcji w wątku" + Thread.currentThread());
        conn.commit();
        return result;
      } catch (Throwable e) {
        conn.rollback();
        e.printStackTrace(System.err);
        return null;
      } finally {
        conn.setAutoCommit(autoCommit);
      }
      // return body.apply(conn);
    } catch (SQLException e) {
      e.printStackTrace(System.err);
      return null;
    }
  }

  @Nullable
  public static <T> T executingQuery(Connection conn
      , String query
      , @NotNull Function<ResultSet, T> body) {

    try (var stmt = conn.createStatement()) {
      try (var rs = stmt.executeQuery(query)) {
        return body.apply(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace(System.err);
      return null;
    }
  }

  @Nullable
  public static <T> T execQuery1(Connection conn
      , String query
      , String columnLabel) {

    try (var stmt = conn.createStatement()) {
      try (var rs = stmt.executeQuery(query)) {
        if (rs.next()) {
          return (T) rs.getObject(columnLabel);
        }
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace(System.err);
      return null;
    }
  }

  public static int execUpdate(Connection conn
      , String query
      , @NotNull Object... args) {
    try (var stmt = conn.prepareStatement(query)) {
      for (int i = 0; i < args.length; i++) {
        stmt.setObject(i+1, args[i]);
      }
      return stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace(System.err);
      return 0;
    }
  }

  // 3. Ustanawianie, zatwierdzanie i wycofywanie traksakcji
  // 4. Ustawianie poziomów izolacji transakcji
  // 5. Reagowanie na powtórzenia transakcji

}
