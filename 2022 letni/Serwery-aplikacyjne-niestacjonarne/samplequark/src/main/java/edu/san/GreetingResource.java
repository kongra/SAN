package edu.san;

import java.util.Map;
import java.util.Objects;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
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
@SessionScoped
public class GreetingResource {

  private final HelloWorker helloWorker;

  @Inject
  public GreetingResource(HelloWorker helloWorker) {
    this.helloWorker = Objects.requireNonNull(helloWorker);
    LOG.info(
        "GreetingResource::constructor(helloWorker) " + helloWorker.getClass());
  }

  @GET
  @Path("/{message}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response hello(@PathParam("message") String message) {
    LOG.info("GreetingResource::hello()");

    final var minimumLength = 2;
    message = message.trim();
    if (message.length() < minimumLength) {
      return Response.status(Status.BAD_REQUEST).build();
    }

    var json = Map.of("hello", message + helloWorker.sayHello());
    return Response.ok(json).build();
  }

  private static final Logger LOG = LoggerFactory
      .getLogger(GreetingResource.class);
}
