package edu.san.authorization.inbound;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authorization")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class AuthorizationResource {

  @POST
  @Path("/sign-up")
  Response signUp(@Valid SignUpData signUpData) {
    // TODO: Use JSON-P for Response

    return null;
  }

}
