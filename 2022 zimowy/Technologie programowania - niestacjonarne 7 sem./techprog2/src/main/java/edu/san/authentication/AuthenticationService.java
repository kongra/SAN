package edu.san.authentication;

import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;

import edu.san.authentication.outbound.ProfileRepositoryInterface;

@ApplicationScoped
public class AuthenticationService {

  private final ProfileRepositoryInterface profileRepository;

  @Inject
  AuthenticationService(ProfileRepositoryInterface profileRepository) {
    Objects.requireNonNull(profileRepository);
    this.profileRepository = profileRepository;
  }

  public Optional<ProfileId> signUp(@Valid SignUpData signUpData) {
    return profileRepository.signUp(signUpData);
  }

}
