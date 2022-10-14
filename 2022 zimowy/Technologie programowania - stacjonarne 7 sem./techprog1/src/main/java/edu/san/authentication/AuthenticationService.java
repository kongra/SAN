package edu.san.authentication;

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import edu.san.authentication.outbound.ProfileRepositoryInterface;

@ApplicationScoped
public class AuthenticationService {

  private final ProfileRepositoryInterface profileRepository;

  @Inject
  AuthenticationService(ProfileRepositoryInterface profileRepository) {
    Objects.requireNonNull(profileRepository);
    this.profileRepository = profileRepository;
  }

  public ProfileId signUp(SignUpData signUpData) {
    return profileRepository.signUp(signUpData);
  }

}
