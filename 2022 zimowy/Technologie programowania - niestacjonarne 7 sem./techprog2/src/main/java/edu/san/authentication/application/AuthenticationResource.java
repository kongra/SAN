package edu.san.authentication.application;

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
import javax.ws.rs.core.Response.Status;

import edu.san.authentication.core.ProfileId;
import edu.san.authentication.ports.in.AuthenticationService;
import telsos.string.Email;
import telsos.string.NonBlank;

@Path("/authentication")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class AuthenticationResource {

  private final AuthenticationService autenticationService;

  @Inject
  AuthenticationResource(AuthenticationService authenticationService) {
    Objects.requireNonNull(authenticationService);
    autenticationService = authenticationService;
  }

  @POST
  @Path("/sign-up")
  public Response signUp(@Valid SignUpData signUpData) {
    final var email = Email.of(signUpData.getEmail()).orElseThrow();
    final var firstName = NonBlank.of(signUpData.getFirstName()).orElseThrow();
    final var lastName = NonBlank.of(signUpData.getLastName()).orElseThrow();

    final var profileId = autenticationService.signUp(email, firstName,
        lastName);
    return profileId
        .map(AuthenticationResource::signUpSuccess)
        .orElseGet(AuthenticationResource::signUpFailure);
  }

  private static Response signUpSuccess(ProfileId profileId) {
    final var result = Json.createObjectBuilder()
        .add("profileId", profileId.value())
        .build();

    return Response.ok(result)
        .status(Status.CREATED)
        .build();
  }

  private static Response signUpFailure() {
    final var result = Json.createObjectBuilder()
        .add("errorMessage", "email in use")
        .build();

    return Response.ok(result)
        .status(Status.BAD_REQUEST)
        .build();
  }

}
