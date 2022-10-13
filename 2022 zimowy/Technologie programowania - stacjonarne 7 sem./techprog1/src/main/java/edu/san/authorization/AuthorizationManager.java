package edu.san.authorization;

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import edu.san.authorization.outbound.IProfileRepository;

@ApplicationScoped
public class AuthorizationManager {

  private final IProfileRepository profileRepository;

  @Inject
  AuthorizationManager(IProfileRepository profileRepository) {
    Objects.requireNonNull(profileRepository);
    this.profileRepository = profileRepository;
  }

  public ProfileId signUp(SignUpData signUpData) {
    return profileRepository.signUp(signUpData);
  }

}
