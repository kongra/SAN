package edu.san.authentication.inbound;

import java.util.Objects;

import javax.inject.Inject;
import javax.json.Json;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.san.authentication.AuthenticationService;
import edu.san.authentication.SignUpData;

@Path("/authentication")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class AuthenticationResource {

  private final AuthenticationService authorizationService;

  @Inject
  AuthenticationResource(AuthenticationService authorizationService) {
    Objects.requireNonNull(authorizationService);
    this.authorizationService = authorizationService;
  }

  @POST
  @Path("/sign-up")
  public Response signUp(@Valid SignUpData signUpData) {
    final var profileId = authorizationService.signUp(signUpData);
    final var result = Json.createObjectBuilder()
        .add("profileId", profileId.value())
        .build();

    return Response.ok(result).build();
  }

}
