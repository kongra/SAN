// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profile;

import java.util.Objects;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/profile")
public class ProfileResource {

  private final ProfileManager profileManager;

  public ProfileResource(ProfileManager profileManager) {
    this.profileManager = Objects.requireNonNull(profileManager);
  }

  public String signIn(String username, String password) {
    return profileManager.isCorrectUser(username, password) ? "OK" : "FAILURE";
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String handleSignIn() {
    return "OK";
  }

}
