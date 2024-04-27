// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/passwords/v1")
interface PasswordsResourceClient {

  @Path("/isPasswordStrong")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response isPasswordStrong(String body);

}
