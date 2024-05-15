// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import java.util.Objects;

import org.jboss.resteasy.reactive.ResponseStatus;

import edu.san.passwords.PasswordsFacade;
import io.quarkus.logging.Log;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/passwords/v1")
class PasswordsResource {

  private final PasswordsFacade passwordsFacade;

  PasswordsResource(PasswordsFacade passwordsFacade) {
    this.passwordsFacade = Objects.requireNonNull(passwordsFacade);
  }

  private static final int STATUS_OK = 200;

  @Path("/isStrongPassword")
  @POST
  @ResponseStatus(value = STATUS_OK)
  public @Valid IsStrongPasswordResult isStrongPassword(
      @Valid NonBlankPasswordQuery query) {
    final var isStrong = passwordsFacade.isStrong(query);

    Log.info("isStrong: %b".formatted(isStrong));
    return new IsStrongPasswordResult(isStrong);
  }

}
