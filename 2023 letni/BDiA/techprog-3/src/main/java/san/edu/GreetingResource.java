package san.edu;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

  private final DBInterface db;

  public GreetingResource(DBInterface db) {
    this.db = db;
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response hello() {
    return Response.ok("The connection was established "
        + db.testDbConnection() + " times").build();
  }
}