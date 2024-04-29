// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import java.util.Optional;
import java.util.stream.Stream;

import edu.san.profiles.MutableProfilesRepository;
import edu.san.profiles.Password;
import edu.san.profiles.Profile;
import edu.san.profiles.ProfileId;
import edu.san.profiles.Username;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class MutableProfilesRepositoryImpl implements MutableProfilesRepository {

  @Override
  public Stream<Profile> getAllProfiles() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<Profile> findProfileByUsername(Username username) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public ProfileId createProfile(Username username, Password password) {
    // TODO Auto-generated method stub
    return null;
  }

}
