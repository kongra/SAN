package edu.san.authentication.control;

import java.util.Optional;

import edu.san.hexagonal.Port;
import edu.san.hexagonal.PortOrAdapterType;
import telsos.string.Email;
import telsos.string.NonBlank;

@Port(PortOrAdapterType.OUTBOUND)
public interface ProfileRepository {

  ProfileId createProfile(Email email, NonBlank firstName, NonBlank lastName);

  Optional<ProfileId> findProfileIdByEmail(Email email);

  Optional<ProfileDto> findProfileDtoByEmail(Email email);

  Optional<ProfileId> deleteProfileByEmail(Email email);

}
