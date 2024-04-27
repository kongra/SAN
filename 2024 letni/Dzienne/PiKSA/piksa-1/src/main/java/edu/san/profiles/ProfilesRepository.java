// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles;

import java.util.Optional;
import java.util.stream.Stream;

public interface ProfilesRepository {

  Stream<Profile> getAllProfiles();

  Optional<Profile> findProfileByUsername(Username username);

}