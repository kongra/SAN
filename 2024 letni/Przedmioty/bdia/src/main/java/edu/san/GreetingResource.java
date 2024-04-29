package edu.san;

import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

import javax.sql.DataSource;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

  private static final String SQL_SELECT_1 = "select first_name from persons where id=?";

  private static final String SQL_INSERT_1 = "insert into persons(first_name) values (?)";

  private final DataSource dataSource;

  public GreetingResource(DataSource dataSource) {
    this.dataSource = Objects.requireNonNull(dataSource);
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Transactional
  @RunOnVirtualThread
  public String hello() {
    try (var conn = dataSource.getConnection()) {
      try (var stmt = conn
          .prepareStatement(SQL_INSERT_1)) {

        final var firstName = "JJ-" + UUID.randomUUID().toString() +
            Thread.currentThread().toString();

        stmt.setString(1, firstName);
        stmt.execute();

        return "Hello from JJ-2";
      }
    } catch (final SQLException e) {
      Log.error(e);
      return "";
    }
  }

  public String query() {
    try (var conn = dataSource.getConnection()) {
      try (var stmt = conn
          .prepareStatement(SQL_SELECT_1)) {
        stmt.setInt(1, 1);
        try (var rs = stmt.executeQuery()) {
          rs.next();
          return "Hello from " + rs.getString("first_name");
        }
      }
    } catch (final SQLException e) {
      Log.error(e);
      return "";
    }
  }
}
