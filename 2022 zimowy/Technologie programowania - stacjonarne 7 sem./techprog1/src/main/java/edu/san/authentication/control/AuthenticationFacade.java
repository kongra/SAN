package edu.san.authentication.control;

import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import telsos.string.Email;
import telsos.string.NonBlank;

@ApplicationScoped
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

    if (optionalProfileId.isEmpty())
      return Optional.of(profileRepository
          .createProfile(email, firstName, lastName));
    return Optional.empty();
  }

  public Optional<ProfileDto> findProfileByEmail(Email email) {
    return profileRepository.findProfileDtoByEmail(email);
  }

}
