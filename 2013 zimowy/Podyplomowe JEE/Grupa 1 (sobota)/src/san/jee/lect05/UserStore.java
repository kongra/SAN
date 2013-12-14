package san.jee.lect05;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static san.util.ExceptionUtils.sneakyThrow;

public class UserStore implements Closeable {

  static {
    try {
      Class.forName("org.postgresql.Driver");
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace(System.err);
    }
  }

  public static class User {

    public final String name;

    public final String surname;

    public final long id;

    private User(String name, String surname, long id) {
      this.name = name;
      this.surname = surname;
      this.id = id;
    }

    @Override
    public String toString() {
      return "User [id=" + id + ", name=" + name + ", surname=" + surname + "]";
    }

  }

  private Connection conn;

  public UserStore() {
    try {
      this.conn =
          DriverManager.getConnection("jdbc:postgresql://localhost/JEE1",
            "jee1", "jee1");
      System.out.println("Ustanowiłem połączenie " + this.conn);
    }
    catch (SQLException e) {
      sneakyThrow(e);
    }
  }

  public Long add(String name, String surname) {
    final boolean isAutoCommit;
    try {
      isAutoCommit = conn.getAutoCommit();
      if (isAutoCommit) {
        conn.setAutoCommit(false);
      }
    }
    catch (SQLException e) {
      sneakyThrow(e);
      return null;
    }

    if (null != findByName(name)) {
      return null;
    }

    try (Statement stmt = conn.createStatement()) {
      String query =
          "insert into emps(name, surname) values ('" + name + "', '" + surname
              + "')";
      stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
      try (ResultSet rs = stmt.getGeneratedKeys()) {
        rs.next();
        long id = rs.getLong("id");

        conn.commit();
        return id;
      }
    }
    catch (SQLException e) {
      sneakyThrow(e);
      return null;
    }
    finally {
      try {
        if (isAutoCommit) {
          conn.setAutoCommit(true);
        }
      }
      catch (SQLException e) {
        sneakyThrow(e);
      }
    }
  }

  @SuppressWarnings("synthetic-access")
  public User findByName(String name) {
    try (Statement stmt = conn.createStatement()) {
      try (ResultSet rs =
          stmt.executeQuery("select id, surname from emps where name='" + name
              + "'")) {

        if (rs.next()) {
          long id = rs.getLong("id");
          String surname = rs.getString("surname");
          return new User(name, surname, id);
        }
        return null;
      }
    }
    catch (SQLException e) {
      sneakyThrow(e);
      return null;
    }
  }

  public User findById(long id) {
    // TODO:
    return null;
  }

  @Override
  public void close() {
    if (conn != null) {
      try {
        System.out.println("Zwalniam połączenie " + conn);
        conn.close();
      }
      catch (SQLException e) {
        sneakyThrow(e);
      }
    }
  }

}
