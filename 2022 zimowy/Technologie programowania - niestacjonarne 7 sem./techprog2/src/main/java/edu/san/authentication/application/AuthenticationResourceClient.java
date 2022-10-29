package edu.san.authentication.application;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authentication")
@Consumes(MediaType.APPLICATION_JSON)
public interface AuthenticationResourceClient {

  @POST
  @Path("/sign-up")
  Response signUp(JsonObject signUpData);

}
