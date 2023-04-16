// © 2023 Konrad Grzanek <kongra@gmail.com>
package san.edu.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;

import javax.sql.DataSource;

public final class TXContext {

  public static <T> T eval(Connection connection, IsolationLevel isolationLevel,
      Function<TXContext, T> expression) throws SQLException {

    final var autoCommitOriginal = connection.getAutoCommit();
    final var transactionIsolationOriginal = connection
        .getTransactionIsolation();

    connection.setAutoCommit(false);
    connection.setTransactionIsolation(isolationLevel.value);

    try {
      final var result = expression
          .apply(new TXContext(connection, isolationLevel));
      connection.commit();
      return result;
    } catch (final RuntimeException t) {
      connection.rollback();
      throw t;
    } finally {
      connection.setAutoCommit(autoCommitOriginal);
      connection.setTransactionIsolation(transactionIsolationOriginal);
    }
  }

  public static <T> T eval(DataSource dataSource, IsolationLevel isolationLevel,
      Function<TXContext, T> expression) throws SQLException {
    try (var connection = dataSource.getConnection()) {
      return eval(connection, isolationLevel, expression);
    }
  }

  private final Connection connection;

  private final IsolationLevel isolationLevel;

  private TXContext(Connection connection, IsolationLevel isolationLevel) {
    this.connection = connection;
    this.isolationLevel = isolationLevel;
  }

  public Connection getConnection() {
    return connection;
  }

  public IsolationLevel getIsolationLevel() {
    return isolationLevel;
  }

}
