// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfileManager {

  private final ProfileRepository profileRepository;

  public ProfileManager(ProfileRepository profileRepository) {
    this.profileRepository = Objects.requireNonNull(profileRepository);
  }

  public boolean isCorrectUser(String username, String password) {
    return profileRepository
        .findProfileByUsername(username)
        .flatMap(onlyWithPassword(password))
        .isPresent();
  }

  private static Function<Profile, Optional<Profile>> onlyWithPassword(
      String password) {
    return profile -> onlyWithPassword(profile, password);
  }

  private static Optional<Profile> onlyWithPassword(Profile profile,
      String password) {
    return password.equals(profile.getPassword()) ? Optional.of(profile)
        : Optional.empty();
  }

}
