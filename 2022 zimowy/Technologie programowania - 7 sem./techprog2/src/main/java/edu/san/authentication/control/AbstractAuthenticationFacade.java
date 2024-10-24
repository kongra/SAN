// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.control;

import java.util.Objects;
import java.util.Optional;

import edu.san.transactions.control.Transactor;
import telsos.architecture.hexagonal.annotations.Port;
import telsos.architecture.hexagonal.annotations.PortType;
import telsos.string.Email;
import telsos.string.NonBlank;

@Port(PortType.INPUT)
public abstract class AbstractAuthenticationFacade {

  protected abstract ProfileRepository profileRepository();

  protected abstract Transactor transactor();

  public Optional<ProfileId> signUpB2C(
      Email email,
      FirstName firstName,
      LastName lastName,
      ProfileKind profileKind,
      NonBlank address) {

    Objects.requireNonNull(email);
    Objects.requireNonNull(firstName);
    Objects.requireNonNull(lastName);
    Objects.requireNonNull(profileKind);
    Objects.requireNonNull(address);

    final var existingProfileId = profileRepository()
        .findProfileIdByEmail(email);

    if (existingProfileId.isPresent())
      return Optional.empty();

    final var newProfileId = profileRepository()
        .createB2C(email, firstName, lastName, profileKind, address);
    return Optional.of(newProfileId);
  }

  public Optional<ProfileId> signUpB2B(
      Email email,
      ProfileKind profileKind,
      NonBlank address,
      NonBlank regon) {

    Objects.requireNonNull(email);
    Objects.requireNonNull(profileKind);
    Objects.requireNonNull(address);
    Objects.requireNonNull(regon);

    final var existingProfileId = profileRepository()
        .findProfileIdByEmail(email);

    if (existingProfileId.isPresent())
      return Optional.empty();

    final var newProfileId = profileRepository()
        .createB2B(email, profileKind, address, regon);
    return Optional.of(newProfileId);
  }

  public Optional<B2CDto> findB2CByEmail(Email email) {
    return profileRepository().findB2CDtoByEmail(email);
  }

}
