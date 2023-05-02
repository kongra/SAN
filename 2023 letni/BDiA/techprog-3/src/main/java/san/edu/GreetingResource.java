package san.edu;

import java.sql.SQLException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

  private final DBInterface db;

  private final TestComponent testComponent;

  public GreetingResource(DBInterface db, TestComponent testComponent) {
    this.db = db;
    this.testComponent = testComponent;
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response hello() throws SQLException {
    testComponent.greet();
    return Response.ok("We have table1 with "
        + db.testTxContext() + " elements").build();
  }
}