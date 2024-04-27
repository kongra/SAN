// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import java.util.Objects;

import edu.san.profiles.ProfilesFacade;
import edu.san.profiles.ProfilesParser;
import edu.san.profiles.ProfilesRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class ProfilesFacadeImpl extends ProfilesFacade {

  private final ProfilesParser profilesParser;

  ProfilesFacadeImpl(
      ProfilesRepository profilesRepository,
      ProfilesParser profilesParser) {
    super(profilesRepository);
    this.profilesParser = Objects.requireNonNull(profilesParser);
  }

  @Override
  public ProfilesParser profilesParser() {
    return profilesParser;
  }

}
