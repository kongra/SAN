package edu.san;

import java.util.Map;
import java.util.Objects;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

  private final Greeter greeter;

  GreetingResource(Greeter greeter) {
    this.greeter = Objects.requireNonNull(greeter);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response hello() {
    final var json = Map.of("greetingText", greeter.getGreetingText());
    return Response.ok(json).build();
  }
}