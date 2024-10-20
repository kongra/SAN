// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.control;

import java.util.Optional;

import telsos.architecture.hexagonal.annotations.Port;
import telsos.architecture.hexagonal.annotations.PortType;
import telsos.string.Email;
import telsos.string.NonBlank;

@Port(PortType.OUTPUT)
public interface ProfileRepository {

  ProfileId createB2C(
      Email email,
      FirstName firstName,
      LastName lastName,
      ProfileKind profileKind,
      NonBlank address);

  ProfileId createB2B(
      Email email,
      ProfileKind profileKind,
      NonBlank address,
      NonBlank regon);

  Optional<ProfileId> findProfileIdByEmail(Email email);

  Optional<B2CDto> findB2CDtoByEmail(Email email);

  Optional<ProfileId> deleteProfileByEmail(Email email);

}
