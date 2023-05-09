package edu.san;

import java.io.StringReader;
import java.util.Objects;

import jakarta.json.Json;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

  private final Greeter greeter;

  GreetingResource(Greeter greeter) {
    this.greeter = Objects.requireNonNull(greeter);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response hello(String body) {
    try (final var jsonReader = Json.createReader(new StringReader(body))) {
      final var bodyJson = jsonReader.readObject();
      final var name = bodyJson.getString("name");
      final var result = Json.createObjectBuilder()
          .add("greeting", greeter.greetMe(name))
          .build();

      return Response.ok(result.toString()).build();
    }
  }
}
