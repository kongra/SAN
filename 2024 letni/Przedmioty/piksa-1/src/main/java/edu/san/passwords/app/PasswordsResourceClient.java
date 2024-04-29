// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/passwords/v1")
@RegisterRestClient
interface PasswordsResourceClient {

  @Path("/isStrongPassword")
  @POST
  IsPasswordStrongResponse isPasswordStrong(NonBlankPasswordInput input);

  static class IsPasswordStrongResponse {
    boolean isStrong;
  }

}
