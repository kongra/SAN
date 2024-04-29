// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import java.util.Map;

import edu.san.passwords.PasswordsFacade;
import edu.san.passwords.PasswordsStrengthAnalyzer;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import telsos.string.NonBlank;

@Path("/passwords/v1")
class PasswordsResource {

  private final PasswordsFacade passwordsFacade;

  PasswordsResource(PasswordsStrengthAnalyzer passwordsStrengthAnalyzer) {
    passwordsFacade = new PasswordsFacade(passwordsStrengthAnalyzer);
  }

  @Path("/isPasswordStrong")
  @POST
  public Response isPasswordStrong(
      @Valid PasswordsResourceInput passwordResourceInput) {

    final var password = NonBlank.of(passwordResourceInput.password)
        .orElseThrow(IllegalArgumentException::new);

    final var isStrong = passwordsFacade.isStrong(password);
    return Response.ok(Map.of("isStrong", isStrong)).build();
  }

}
