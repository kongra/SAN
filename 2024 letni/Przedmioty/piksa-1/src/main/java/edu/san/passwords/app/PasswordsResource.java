// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import org.jboss.resteasy.reactive.ResponseStatus;

import edu.san.passwords.PasswordsFacade;
import edu.san.passwords.PasswordsStrengthAnalyzer;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/passwords/v1")
class PasswordsResource {

  private final PasswordsFacade passwordsFacade;

  PasswordsResource(PasswordsStrengthAnalyzer passwordsStrengthAnalyzer) {
    passwordsFacade = new PasswordsFacade(passwordsStrengthAnalyzer);
  }

  private static final int STATUS_OK = 200;

  @Path("/isStrongPassword")
  @POST
  @ResponseStatus(value = STATUS_OK)
  public @Valid IsStrongPasswordOutput isStrongPassword(
      @Valid NonBlankPasswordInput input) {
    final var isStrong = passwordsFacade.isStrong(input.asNonBlank());
    return new IsStrongPasswordOutput(isStrong);
  }

}
