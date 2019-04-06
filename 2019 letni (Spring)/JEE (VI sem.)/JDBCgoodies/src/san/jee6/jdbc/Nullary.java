package san.jee6.jdbc;

import java.sql.SQLException;

public interface Nullary<T> {

  T call() throws SQLException;

  static <T> T silentlySQL(Nullary<T> body) {
    try {
      return body.call();
    } catch(SQLException e) {
      e.printStackTrace(System.err);
      return null;
    }
  }

}
