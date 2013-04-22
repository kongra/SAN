package san.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCWrites {

  public static int update(Connection connection, final String query) {
    final IntRef count = new IntRef();
    new WithJDBCStatement(connection) {
      @Override
      public void run() {
        try {
          count.value = statement.executeUpdate(query);
        }
        catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    };
    return count.value;
  }

}
