package edu.san.profiles.app;

import java.util.Map;
import java.util.Objects;

import edu.san.profiles.ProfilesRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/profiles/v1")
class ProfilesResource {

  private final ProfilesRepository profilesRepository;

  ProfilesResource(ProfilesRepository profilesRepository) {
    this.profilesRepository = Objects.requireNonNull(profilesRepository);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllProfiles() {
    profilesRepository.getAllProfiles();
    final var profilesJson = Map.of();

    return Response.ok(profilesJson).build();
  }
}
