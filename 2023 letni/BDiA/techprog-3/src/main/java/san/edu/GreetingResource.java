package san.edu;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

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