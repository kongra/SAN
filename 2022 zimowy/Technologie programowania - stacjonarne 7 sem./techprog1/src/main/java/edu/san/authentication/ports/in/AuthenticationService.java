package edu.san.authentication.ports.in;

import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import edu.san.authentication.core.ProfileId;
import edu.san.authentication.ports.out.ProfileRepository;
import telsos.string.Email;
import telsos.string.NonBlank;

@ApplicationScoped
public class AuthenticationService {

  private final ProfileRepository profileRepository;

  AuthenticationService(ProfileRepository profileRepository) {
    Objects.requireNonNull(profileRepository);
    this.profileRepository = profileRepository;
  }

  public Optional<ProfileId> signUp(Email email, NonBlank firstName, NonBlank lastName) {
    return profileRepository.signUp(email, firstName, lastName);
  }

}
