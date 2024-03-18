// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profile;

import java.util.Optional;

public interface ProfileRepository {

  Optional<Profile> findProfileByUsername(String username);

}