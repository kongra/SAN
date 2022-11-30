// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.control;

import java.util.Objects;
import java.util.Optional;

import edu.san.transactions.control.Transactor;
import lombok.extern.slf4j.Slf4j;
import telsos.architecture.hexagonal.annotations.Port;
import telsos.architecture.hexagonal.annotations.PortType;
import telsos.string.Email;

@Port(PortType.INPUT)
@Slf4j
public abstract class AbstractAuthenticationFacade {

  protected abstract ProfileRepository profileRepository();

  protected abstract Transactor transactor();

  public Optional<ProfileId> signUp(
      Email email,
      FirstName firstName,
      LastName lastName,
      ProfileKind profileKind) {

    Objects.requireNonNull(email);
    Objects.requireNonNull(firstName);
    Objects.requireNonNull(lastName);
    Objects.requireNonNull(profileKind);

    log.info("isActiveTransaction " + transactor().isActiveTransaction());

    final var existingProfileId = profileRepository()
        .findProfileIdByEmail(email);

    if (existingProfileId.isPresent())
      return Optional.empty();

    final var newProfileId = profileRepository()
        .createProfile(email, firstName, lastName, profileKind);
    return Optional.of(newProfileId);
  }

  public Optional<ProfileDto> findProfileByEmail(Email email) {
    return profileRepository().findProfileDtoByEmail(email);
  }

}
