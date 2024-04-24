// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public final class ProfilesFacade {

  private final ProfilesRepository profilesRepository;

  private final ProfilesFactory profilesFactory;

  public ProfilesFacade(
      ProfilesRepository profilesRepository,
      ProfilesFactory profilesFactory) {
    this.profilesRepository = Objects.requireNonNull(profilesRepository);
    this.profilesFactory    = Objects.requireNonNull(profilesFactory);
  }

  public boolean isCorrectUser(Username username, Password password) {
    return profilesRepository
        .findProfileByUsername(username)
        .flatMap(onlyWithPassword(password))
        .isPresent();
  }

  public Optional<Username> createUsername(String username) {
    return profilesFactory.createUsername(username);
  }

  public Optional<Password> createPassword(String password) {
    return profilesFactory.createPassword(password);
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
