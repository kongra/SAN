package edu.san.authentication.boundary;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("authentication")
@Consumes(MediaType.APPLICATION_JSON)
public interface AuthenticationResourceClient {

  @GET
  @Path("profile-by-email/{email}")
  Response findProfileByEmail(@PathParam("email") String email);
  
  @POST
  @Path("sign-up")
  Response signUp(JsonObject signUpData);

}
