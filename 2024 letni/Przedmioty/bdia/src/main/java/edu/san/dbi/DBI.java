// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.dbi;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import edu.san.dbi.RSExpr.RSStepResult;
import io.quarkus.logging.Log;

public interface DBI {

  static <T> T evalWithConnection(DataSource dataSource, Expr<T> expr) {
    try (var conn = dataSource.getConnection()) {
      return expr.eval(conn);
    } catch (final SQLException e) {
      Log.error(e);
      throw new DBIException(e);
    }
  }

  static void execWithConnection(DataSource dataSource, Stmt stmt) {
    evalWithConnection(dataSource, stmt);
  }

  static <T> T evalReadCommitted(DataSource dataSource, Expr<T> expr) {
    return evalWithinTransaction(dataSource,
        Connection.TRANSACTION_READ_COMMITTED,
        expr);
  }

  static void execReadCommitted(DataSource dataSource, Stmt stmt) {
    evalReadCommitted(dataSource, stmt);
  }

  static <T> T evalSerializable(DataSource dataSource, Expr<T> expr) {
    return evalWithinTransaction(dataSource,
        Connection.TRANSACTION_SERIALIZABLE,
        expr);
  }

  static void execSerializable(DataSource dataSource, Stmt stmt) {
    evalSerializable(dataSource, stmt);
  }

  static <T> T evalWithinTransaction(DataSource dataSource, int isolationLevel,
      Expr<T> expr) {
    return evalWithConnection(dataSource,
        conn -> evalWithinTransaction(conn, isolationLevel, expr));
  }

  static <T> T evalWithinTransaction(Connection conn, int isolationLevel,
      Expr<T> expr) {
    var autoCommitOriginal = true;
    var isolationLevelOriginal = 0;
    try {
      autoCommitOriginal     = conn.getAutoCommit();
      isolationLevelOriginal = conn.getTransactionIsolation();

      conn.setTransactionIsolation(isolationLevel);
      conn.setAutoCommit(false);

      return expr.eval(conn);
    } catch (final SQLException e) {
      Log.error(e);
      throw new DBIException(e);
    } finally {
      try {
        conn.setAutoCommit(autoCommitOriginal);
        conn.setTransactionIsolation(isolationLevelOriginal);
      } catch (final SQLException e) {
        Log.error(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  static <T> T evalWithResultSet(Statement stmt, String query, RSExpr<T> expr)
      throws SQLException {
    T value = null;
    try (var rs = stmt.executeQuery(query)) {
      while (rs.next()) {
        var result = expr.eval(rs);
        if (result instanceof RSStepResult.Break) {
          break;
        }
        if (result instanceof RSStepResult.Continue) {
          continue;
        }
        if (result instanceof RSStepResult.Result r){
          value = (T) r.value();
        }
      }
    }
    return value;
  }

}
