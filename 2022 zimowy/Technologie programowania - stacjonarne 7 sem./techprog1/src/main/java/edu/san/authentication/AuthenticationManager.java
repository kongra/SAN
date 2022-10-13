package edu.san.authentication;

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import edu.san.authentication.outbound.IProfileRepository;

@ApplicationScoped
public class AuthenticationManager {

  private final IProfileRepository profileRepository;

  @Inject
  AuthenticationManager(IProfileRepository profileRepository) {
    Objects.requireNonNull(profileRepository);
    this.profileRepository = profileRepository;
  }

  public ProfileId signUp(SignUpData signUpData) {
    return profileRepository.signUp(signUpData);
  }

}
