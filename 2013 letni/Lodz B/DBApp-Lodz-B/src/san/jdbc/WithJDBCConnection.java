package san.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class WithJDBCConnection {

  protected final Connection connection;

  public WithJDBCConnection() {
    this(createDefaultConnection());
  }

  private static Connection createDefaultConnection() {
    try {
      return DriverManager.getConnection("jdbc:postgresql://localhost/MAAS",
        "scott", "tiger");
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public WithJDBCConnection(Connection connection) {
    this.connection = connection;

    try {
      run();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        connection.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  protected abstract void run();

}
