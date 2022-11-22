package edu.san.authentication.control;

import java.util.Optional;

import edu.san.transactions.control.Transactor;
import lombok.extern.slf4j.Slf4j;
import telsos.architecture.hexagonal.annotations.Port;
import telsos.architecture.hexagonal.annotations.PortType;
import telsos.string.Email;
import telsos.string.NonBlank;

@Port(PortType.INPUT)
@Slf4j
public abstract class AbstractAuthenticationFacade {

  protected abstract ProfileRepository profileRepository();

  protected abstract Transactor transactor();

  public Optional<ProfileId> signUp(
      Email email,
      NonBlank firstName,
      NonBlank lastName,
      ProfileKind profileKind) {

    log.info("isActiveTransaction " + transactor().isActiveTransaction());

    final var optionalProfileId = profileRepository()
        .findProfileIdByEmail(email);

    if (optionalProfileId.isEmpty()) {
      final var profileId = profileRepository()
          .createProfile(email, firstName, lastName, profileKind);
      return Optional.of(profileId);
    }
    return Optional.empty();
  }

  public Optional<ProfileDto> findProfileByEmail(Email email) {
    return profileRepository().findProfileDtoByEmail(email);
  }

}
