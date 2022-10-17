package edu.san.authentication.outbound;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;

import edu.san.authentication.ProfileId;
import edu.san.authentication.SignUpData;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
@Transactional
public class ProfileRepository
    implements PanacheRepository<ProfileEntity>, ProfileRepositoryInterface {

  @Override
  public Optional<ProfileId> signUp(@Valid SignUpData signUpData) {
    final var profileEntity = new ProfileEntity(/* id */ null,
        signUpData.getEmail(),
        signUpData.getFirstName(),
        signUpData.getLastName());

    persist(profileEntity);
    return Optional.of(new ProfileId(profileEntity.id));
  }

}
