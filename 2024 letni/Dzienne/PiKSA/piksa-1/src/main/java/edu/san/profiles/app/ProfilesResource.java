package edu.san.profiles.app;

import java.util.Map;
import java.util.Objects;

import edu.san.profiles.ProfileRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/profiles")
class ProfilesResource {

  private final ProfileRepository profileRepository;

  ProfilesResource(ProfileRepository profileRepository) {
    this.profileRepository = Objects.requireNonNull(profileRepository);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllProfiles() {
    var profiles = profileRepository.getAllProfiles();
    var profilesJson = Map.of();

    return Response.ok(profilesJson).build();
  }
}
