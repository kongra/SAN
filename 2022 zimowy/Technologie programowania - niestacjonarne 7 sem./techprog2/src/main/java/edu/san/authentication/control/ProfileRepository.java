package edu.san.authentication.control;

import java.util.Optional;

import telsos.string.Email;
import telsos.string.NonBlank;

public interface ProfileRepository {

  ProfileId createProfile(Email email, NonBlank firstName, NonBlank lastName);

  Optional<ProfileId> findProfileIdByEmail(Email email);

  Optional<ProfileDto> findProfileDtoByEmail(Email email);

  Optional<ProfileId> deleteProfileByEmail(Email email);

}
