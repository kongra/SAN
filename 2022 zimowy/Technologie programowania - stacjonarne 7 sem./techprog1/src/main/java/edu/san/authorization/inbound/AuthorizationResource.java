package edu.san.authorization.inbound;

import java.util.Objects;

import javax.json.Json;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.san.authorization.AuthorizationManager;
import edu.san.authorization.SignUpData;

@Path("/authorization")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class AuthorizationResource {

  private final AuthorizationManager authorizationManager;

  AuthorizationResource(AuthorizationManager authorizationManager) {
    Objects.requireNonNull(authorizationManager);
    this.authorizationManager = authorizationManager;
  }

  @POST
  @Path("/sign-up")
  Response signUp(@Valid SignUpData signUpData) {
    final var profileId = authorizationManager.signUp(signUpData);
    final var result = Json.createObjectBuilder()
        .add("profileId", profileId.value())
        .build();

    return Response.ok(result).build();
  }

}
