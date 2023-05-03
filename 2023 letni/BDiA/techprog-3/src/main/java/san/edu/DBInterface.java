// © 2023 Konrad Grzanek <kongra@gmail.com>
package san.edu;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import jakarta.enterprise.context.ApplicationScoped;
import san.edu.db.IsolationLevel;
import san.edu.db.TXContext;

@ApplicationScoped
public class DBInterface {

  private static final AtomicLong count = new AtomicLong(0);

  private final DataSource dataSource;

  public DBInterface(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  private static Optional<Long> selectCountFromTest1(TXContext txContext) {
    Objects.requireNonNull(txContext);
    try (final var stmt = txContext.getConnection().createStatement();
        final var rs = stmt.executeQuery("select count(*) from test1")) {
      return rs.next() ? Optional.of(rs.getLong(1)) : Optional.empty();
    } catch (final SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public long testDbConnection() {
    final var query = "select count(*) from test1";
    try (
        final var conn = dataSource.getConnection();
        final var stmt = conn.createStatement();
        final var rs = stmt.executeQuery(query)) {

      if (rs.next()) {
        final var value = rs.getLong(1);
        count.addAndGet(value);
      }

      return count.incrementAndGet();
    } catch (final SQLException e) {
      e.printStackTrace();
      return count.longValue();
    }
  }

  public Optional<Long> testTxContext() throws SQLException {
    return TXContext.eval(dataSource, IsolationLevel.READ_UNCOMMITTED,
        DBInterface::selectCountFromTest1);
  }
}
