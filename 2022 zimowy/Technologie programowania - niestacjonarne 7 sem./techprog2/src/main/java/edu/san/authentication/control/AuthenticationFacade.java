package edu.san.authentication.control;

import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import edu.san.hexagonal.Port;
import edu.san.hexagonal.PortOrAdapterType;
import lombok.extern.slf4j.Slf4j;
import telsos.string.Email;
import telsos.string.NonBlank;

@ApplicationScoped
@Port(PortOrAdapterType.INBOUND)
@Slf4j
public class AuthenticationFacade {

  private final ProfileRepository profileRepository;

  private final Transactor transactor;

  AuthenticationFacade(
      ProfileRepository profileRepository,
      Transactor transactor) {

    this.profileRepository = Objects.requireNonNull(profileRepository);
    this.transactor = Objects.requireNonNull(transactor);
  }

  public Optional<ProfileId> signUp(
      Email email, NonBlank firstName, NonBlank lastName) {

    log.info("transactionContext is " + transactor.isActiveTransaction());

    final var optionalProfileId = profileRepository
        .findProfileIdByEmail(email);

    if (optionalProfileId.isEmpty()) {
      final var profileEntity = profileRepository
          .createProfile(email, firstName, lastName);
      return Optional.of(profileEntity);
    }
    return Optional.empty();
  }

  public Optional<ProfileDto> findProfileByEmail(Email email) {
    return profileRepository.findProfileDtoByEmail(email);
  }

}
