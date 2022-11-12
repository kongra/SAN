package edu.san.authentication.control;

import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import edu.san.hexagonal.Port;
import edu.san.hexagonal.PortOrAdapterType;
import telsos.string.Email;
import telsos.string.NonBlank;

@ApplicationScoped
@Port(PortOrAdapterType.INBOUND)
public class AuthenticationFacade {

  private final ProfileRepository profileRepository;

  AuthenticationFacade(ProfileRepository profileRepository) {
    Objects.requireNonNull(profileRepository);
    this.profileRepository = profileRepository;
  }

  public Optional<ProfileId> signUp(
      Email email, NonBlank firstName, NonBlank lastName) {

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
