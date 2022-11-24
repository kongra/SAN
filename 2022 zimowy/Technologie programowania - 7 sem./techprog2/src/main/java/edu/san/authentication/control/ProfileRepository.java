// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.control;

import java.util.Optional;

import telsos.architecture.hexagonal.annotations.Port;
import telsos.architecture.hexagonal.annotations.PortType;
import telsos.string.Email;
import telsos.string.NonBlank;

@Port(PortType.OUTPUT)
public interface ProfileRepository {

  ProfileId createProfile(
      Email email,
      NonBlank firstName,
      NonBlank lastName,
      ProfileKind profileKind);

  Optional<ProfileId> findProfileIdByEmail(Email email);

  Optional<ProfileDto> findProfileDtoByEmail(Email email);

  Optional<ProfileId> deleteProfileByEmail(Email email);

}
