package san.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class WithJDBCQueryResults {

  private ResultSet rs;

  public WithJDBCQueryResults(Connection connection, final String query) {
    new WithJDBCStatement(connection) {
      @SuppressWarnings("synthetic-access")
      @Override
      public void run() {
        try {
          WithJDBCQueryResults.this.rs = statement.executeQuery(query);
          WithJDBCQueryResults.this.run();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    };
  }

  public WithJDBCQueryResults(ResultSet rs) {
    this.rs = rs;
    run();
  }

  private void run() throws RuntimeException {
    try {
      while (rs.next()) {
        step();
      }
    }
    catch (SQLException sqlE) {
      sqlE.printStackTrace();
    }
    catch (RuntimeException e) {
      throw e;
    }
    finally {
      if (rs != null) {
        try {
          rs.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public abstract void step();

  @SuppressWarnings("unchecked")
  protected <T> T get(String columnLabel) {
    try {
      return (T) rs.getObject(columnLabel);
    }
    catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
