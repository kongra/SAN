// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles;

import java.util.Optional;
import java.util.stream.Stream;

public interface ProfileRepository {

  Stream<Profile> getAllProfiles();

  Optional<Profile> findProfileByUsername(Username username);

  ProfileValuesFactory newProfileValuesFactory();

}