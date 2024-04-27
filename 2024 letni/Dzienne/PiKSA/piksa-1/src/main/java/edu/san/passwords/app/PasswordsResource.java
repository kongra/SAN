// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import java.io.StringReader;
import java.util.Objects;

import edu.san.passwords.PasswordsFacade;
import io.quarkus.logging.Log;
import jakarta.json.Json;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import telsos.string.NonBlank;

@Path("/passwords/v1")
class PasswordsResource {

  private final PasswordsFacade passwordsFacade;

  PasswordsResource(PasswordsFacade passwordsFacade) {
    this.passwordsFacade = Objects.requireNonNull(passwordsFacade);
  }

  private static Response createBadRequest() {
    return Response.status(Status.BAD_REQUEST).build();
  }

  private Response isPasswordStrongImpl(NonBlank body) {
    try (final var jsonReader = Json.createReader(
        new StringReader(body.value()))) {

      final var input = jsonReader.readObject();
      final var nonBlankPassword = NonBlank.ofTrimmed(
          input.getString("password"));

      return nonBlankPassword.map(password -> {
        final var isStrong = passwordsFacade.isStrong(password);
        final var output = Json.createObjectBuilder()
            .add("isStrong", isStrong).build();
        return Response.ok(output).build();
      }).orElseGet(PasswordsResource::createBadRequest);
    }
  }

  @Path("/isPasswordStrong")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response isPasswordStrong(String body) {
    Log.info("body is " + body);
    return NonBlank
        .ofTrimmed(body)
        .map(this::isPasswordStrongImpl)
        .orElseGet(PasswordsResource::createBadRequest);
  }

}
