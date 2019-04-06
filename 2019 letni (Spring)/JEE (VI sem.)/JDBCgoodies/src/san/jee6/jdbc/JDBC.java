package san.jee6.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static san.jee6.jdbc.Nullary.silentlySQL;

public final class JDBC {

  public static <T> T withTx(final Connection conn,
                             final int isolationLevel,
                             final Nullary<T> body) throws SQLException {

    final int levelOld = silentlySQL(() -> conn.getTransactionIsolation());
    final boolean ac   = silentlySQL(() -> conn.getAutoCommit());
    try {
      conn.setTransactionIsolation(isolationLevel);
      conn.setAutoCommit(false);
      T result = body.call();
      conn.commit();
      return result;
    }
    finally {
      silentlySQL(() -> { conn.setAutoCommit(ac); return null; });
      silentlySQL(() -> { conn.setTransactionIsolation(levelOld); return null;});
    }
  }

  public static void iterQuery(final Connection conn,
                               String sql,
                               Unary<ResultSet, Void> body)
      throws SQLException {
    try (Statement s = conn.createStatement()) {
      try (ResultSet rs = s.executeQuery(sql)) {
        while (rs.next()) {
          body.call(rs);
        }
      }
    }
  }

  public static long execUpdate(final Connection conn,
                                String sql) throws SQLException {
    try (Statement s = conn.createStatement()) {
      return s.executeUpdate(sql);
    }
  }

  public static <T> T restartingSerial(long count,
                                       Nullary<T> body) throws SQLException {
    for (int i = 1; i <= count; i++) {
      try {
        return body.call();
      }
      catch (SQLException e) {
        if(!"40001".equals(e.getSQLState()))
          throw e;

        System.out.println("Restarting SERIALIZABLE");
      }
    }
    return body.call();
  }

  private JDBC() {

  }
}
