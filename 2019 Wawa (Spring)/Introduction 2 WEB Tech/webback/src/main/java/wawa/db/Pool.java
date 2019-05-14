package wawa.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class Pool {

  public static Connection getConnection() {
    try {
      return c3p0.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private static final ComboPooledDataSource c3p0 = new ComboPooledDataSource();

  static {
    try {
      c3p0.setDriverClass("org.postgresql.Driver");
    } catch (PropertyVetoException e) {
      e.printStackTrace(System.err);
    }
    c3p0.setJdbcUrl("jdbc:postgresql://localhost/WAWA");
    c3p0.setUser("wawa");
    c3p0.setPassword("wawa12345");

    c3p0.setMinPoolSize(5);
    c3p0.setInitialPoolSize(5);
    c3p0.setAcquireIncrement(5);
    c3p0.setMaxPoolSize(20);
  }


}
