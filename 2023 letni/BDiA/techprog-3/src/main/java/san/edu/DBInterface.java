// © 2023 Konrad Grzanek <kongra@gmail.com>
package san.edu;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

import san.edu.db.IsolationLevel;
import san.edu.db.TXContext;

@ApplicationScoped
public class DBInterface {

  private static AtomicLong count = new AtomicLong(0);

  private final DataSource dataSource;

  public DBInterface(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public long testDbConnection() {
    final var query = "select count(*) from test1";
    try (
        var conn = dataSource.getConnection();
        var stmt = conn.createStatement();
        var rs = stmt.executeQuery(query)) {

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

  private static Optional<Long> selectCountFromTest1(TXContext txContext) {
    Objects.requireNonNull(txContext);
    try (var stmt = txContext.getConnection().createStatement();
        var rs = stmt.executeQuery("select count(*) from test1")) {
      return rs.next() ? Optional.of(rs.getLong(1)) : Optional.empty();
    } catch (final SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<Long> testTxContext() throws SQLException {
    return TXContext.eval(dataSource, IsolationLevel.READ_UNCOMMITTED,
        DBInterface::selectCountFromTest1);
  }
}
