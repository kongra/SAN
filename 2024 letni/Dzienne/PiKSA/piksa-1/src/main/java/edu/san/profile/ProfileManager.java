// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profile;

import java.util.Optional;
import java.util.function.Function;

public class ProfileManager {

  private final ProfileRepository profileRepository = new ProfileRepositoryImpl();

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
