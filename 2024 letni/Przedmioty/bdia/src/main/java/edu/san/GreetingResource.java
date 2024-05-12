package edu.san;

import java.util.Objects;
import java.util.UUID;

import javax.sql.DataSource;

import edu.san.dbi.DBI;
import io.quarkus.logging.Log;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
class GreetingResource {

  private static final String SQL_SELECT_1 = "select first_name from persons where id=?";

  private static final String SQL_INSERT_1 = "insert into persons(first_name) values (?)";

  private final DataSource dataSource;

  GreetingResource(DataSource dataSource) {
    this.dataSource = Objects.requireNonNull(dataSource);
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @RunOnVirtualThread
  public String hello() {
    return DBI.evalReadCommitted(dataSource, conn -> {
      Log.info("isolation=" + conn.getTransactionIsolation());

      try (var stmt = conn
          .prepareStatement(SQL_INSERT_1)) {

        final var firstName = "JJ-" + UUID.randomUUID().toString() +
            Thread.currentThread().toString();

        stmt.setString(1, firstName);
        stmt.execute();

        return "Hello from JJ-2";
      }
    });
  }

  public String query() {
    return DBI.evalReadCommitted(dataSource, conn -> {
      try (var stmt = conn
          .prepareStatement(SQL_SELECT_1)) {
        stmt.setInt(1, 1);
        try (var rs = stmt.executeQuery()) {
          rs.next();
          return "Hello from " + rs.getString("first_name");
        }
      }
    });
  }
}
