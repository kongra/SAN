// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import java.util.Objects;

import edu.san.profiles.ProfilesFacade;
import edu.san.profiles.ProfilesRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class ProfilesFacadeImpl extends ProfilesFacade {

  private final ProfilesRepository profilesRepository;

  ProfilesFacadeImpl(ProfilesRepository profilesRepository) {
    this.profilesRepository = Objects.requireNonNull(profilesRepository);
  }

  @Override
  protected final ProfilesRepository getProfilesRepository() {
    return profilesRepository;
  }

}
