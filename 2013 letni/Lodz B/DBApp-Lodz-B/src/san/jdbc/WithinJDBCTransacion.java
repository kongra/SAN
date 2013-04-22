package san.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class WithinJDBCTransacion {

  protected final Connection connection;

  public WithinJDBCTransacion(Connection connection) {
    this(connection, Connection.TRANSACTION_READ_COMMITTED);
  }

  public WithinJDBCTransacion(Connection connection, int isolationLevel) {
    this.connection = connection;

    boolean originalAutoCommit = true;
    int originalIsolationLevel = Connection.TRANSACTION_READ_COMMITTED;

    try {
      originalAutoCommit = connection.getAutoCommit();
      originalIsolationLevel = connection.getTransactionIsolation();

      connection.setAutoCommit(false);
      connection.setTransactionIsolation(isolationLevel);
      run();
      commit();
    }
    catch (SQLException e) {
      rollback();
    }
    finally {
      try {
        connection.setAutoCommit(originalAutoCommit);
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        connection.setTransactionIsolation(originalIsolationLevel);
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  protected abstract void run();

  protected final boolean commit() {
    try {
      connection.commit();
      return true;
    }
    catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  protected final boolean rollback() {
    try {
      connection.rollback();
      return true;
    }
    catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
