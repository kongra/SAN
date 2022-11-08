package edu.san.authentication.boundary;

import java.util.Objects;

import javax.inject.Inject;
import javax.json.Json;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.san.authentication.control.AuthenticationFacade;
import edu.san.authentication.control.ProfileId;
import telsos.string.Email;
import telsos.string.NonBlank;

@Path("authentication")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class AuthenticationResource {

  private final AuthenticationFacade autenticationFacade;

  @Inject
  AuthenticationResource(AuthenticationFacade authenticationFacade) {
    Objects.requireNonNull(authenticationFacade);
    autenticationFacade = authenticationFacade;
  }

  @GET
  @Path("profile-by-email/{email}")
  public Response findProfileByEmail(@PathParam("email") String email) {
    final var theEmail = Email.of(email).orElseThrow(BadRequestException::new);
    final var optionalProfileDto = autenticationFacade
        .findProfileByEmail(theEmail);
    
    if (optionalProfileDto.isEmpty()) {
      return Response.ok().build();
    }
    return Response.ok(optionalProfileDto.get()).build();
  }
  
  @POST
  @Path("sign-up")
  public Response signUp(@Valid SignUpDto signUpDto) {
    final var email = Email.of(signUpDto.getEmail()).orElseThrow();
    final var firstName = NonBlank.of(signUpDto.getFirstName()).orElseThrow();
    final var lastName = NonBlank.of(signUpDto.getLastName()).orElseThrow();

    final var profileId = autenticationFacade.signUp(email, firstName, lastName);
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
