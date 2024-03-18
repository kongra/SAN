// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profile;

import java.util.Optional;
import java.util.UUID;

public class ProfileRepositoryFake implements ProfileRepository {

  @Override
  public Optional<Profile> findProfileByUsername(String username) {
    if ("root".equals(username))
      return Optional.empty();

    final var password = UUID.randomUUID().toString();
    return Optional.of(() -> password);
  }

}
