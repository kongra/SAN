// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public abstract class ProfilesFacade {

  private final ProfilesRepository profilesRepository;

  protected ProfilesFacade(ProfilesRepository profilesRepository) {
    this.profilesRepository = Objects.requireNonNull(profilesRepository);
  }

  public abstract ProfilesParser profilesParser();

  public boolean isCorrectUser(Username username, Password password) {
    return profilesRepository
        .findProfileByUsername(username)
        .flatMap(onlyWithPassword(password))
        .isPresent();
  }

  private static Function<Profile, Optional<Profile>> onlyWithPassword(
      Password password) {
    return profile -> onlyWithPassword(profile, password);
  }

  private static Optional<Profile> onlyWithPassword(Profile profile,
      Password password) {
    return password.equals(profile.getPassword()) ? Optional.of(profile)
        : Optional.empty();
  }

}
