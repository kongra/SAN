package edu.san.authentication.ports.out;

import java.util.Optional;

import edu.san.authentication.core.ProfileId;
import telsos.string.Email;
import telsos.string.NonBlank;

public interface ProfileRepository {

  Optional<ProfileId> signUp(Email email, NonBlank firstName,
      NonBlank lastName);

  Optional<ProfileId> profileIdByEmail(Email email);

  Optional<ProfileId> deleteByEmail(Email email);

}
