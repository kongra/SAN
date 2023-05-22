// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
public interface GreetingResourceClient {

  @POST
  @Path("/hello")
  Response hello(JsonObject body);

}
