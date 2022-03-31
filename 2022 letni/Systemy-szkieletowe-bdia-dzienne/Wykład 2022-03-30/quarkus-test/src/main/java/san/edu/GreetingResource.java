package san.edu;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.common.annotation.NonBlocking;

@Path("/hello")
public class GreetingResource {

  @GET
  @NonBlocking
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello RESTEasy ";
  }
}
