package edu.san.authentication.control;

import java.util.Optional;

import telsos.string.Email;
import telsos.string.NonBlank;

public interface ProfileRepository {

  Optional<ProfileId> signUp(Email email, NonBlank firstName, NonBlank lastName);

  Optional<ProfileId> profileIdByEmail(Email email);

  Optional<ProfileId> deleteByEmail(Email email);

}
