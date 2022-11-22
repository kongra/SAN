package edu.san.authentication.boundary;

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;

import edu.san.authentication.control.AbstractAuthenticationFacade;
import edu.san.authentication.control.ProfileRepository;
import edu.san.transactions.control.Transactor;
import telsos.architecture.hexagonal.annotations.Adapter;
import telsos.architecture.hexagonal.annotations.AdapterType;

@Adapter(AdapterType.PRIMARY)
@ApplicationScoped
class AuthenticationFacadeImpl extends AbstractAuthenticationFacade {

  private final ProfileRepository profileRepository;

  private final Transactor transactor;

  AuthenticationFacadeImpl(
      ProfileRepository profileRepository,
      Transactor transactor) {
    this.profileRepository = Objects.requireNonNull(profileRepository);
    this.transactor = Objects.requireNonNull(transactor);
  }

  @Override
  protected final ProfileRepository profileRepository() {
    return profileRepository;
  }

  @Override
  protected final Transactor transactor() {
    return transactor;
  }

}
