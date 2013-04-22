package san.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class WithJDBCStatement {

  protected final Statement statement;

  public WithJDBCStatement(Connection connection) {
    this(createStatement(connection));
  }

  private static Statement createStatement(Connection connection) {
    try {
      return connection.createStatement();
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public WithJDBCStatement(Statement statement) {
    this.statement = statement;
    try {
      run();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        statement.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public abstract void run();
}
