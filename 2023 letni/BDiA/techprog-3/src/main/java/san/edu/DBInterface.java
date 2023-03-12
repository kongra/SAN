// © 2023 Konrad Grzanek <kongra@gmail.com>
package san.edu;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

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

//  private void execSomeLogic(TxContext ctx) {
//    // if () { ... SELECT * ... }
//  }
}
