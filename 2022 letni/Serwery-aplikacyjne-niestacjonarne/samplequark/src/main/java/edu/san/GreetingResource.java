package edu.san;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello")
public class GreetingResource {

  @GET
  @Path("/{message}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response hello(@PathParam("message") String message) {
    log.debug("GreetingResource::hello()");

    message = message.trim();
    if (message.length() < 2) {
      return Response.status(Status.BAD_REQUEST).build();
    }

    var json = Map.of("hello", message);
    return Response.ok(json).build();
  }

  static final Logger log = LoggerFactory.getLogger(GreetingResource.class);
}
