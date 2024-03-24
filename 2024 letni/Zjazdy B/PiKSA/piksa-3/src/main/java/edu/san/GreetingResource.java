package edu.san;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class GreetingResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response hello() {
    JsonObject json = Json
        .createObjectBuilder()
        .add("greeting", "Hello from RESTEasy Reactive")
        .build();
    
    return Response.ok(json.toString()).build();
  }
}
