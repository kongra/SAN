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
public class ProfileRepositoryImpl
    implements PanacheRepository<ProfileEntity>, ProfileRepository {

  @Override
  public Optional<ProfileId> signUp(@Valid SignUpData signUpData) {
    final var existingProfile = findByEmail(signUpData.getEmail());
    final Optional<ProfileId> nothing = Optional.empty();
    return existingProfile.map(profile -> nothing).orElseGet(() -> {
      final var profileEntity = new ProfileEntity(/* id */ null,
          signUpData.getEmail(),
          signUpData.getFirstName(),
          signUpData.getLastName());

      persist(profileEntity);
      return Optional.of(new ProfileId(profileEntity.id));
    });
  }

  @Override
  public Optional<ProfileEntity> findByEmail(String email) {
    return find("email = ?1", email).firstResultOptional();
  }

  @Override
  public boolean deleteByEmail(String email) {
    final var existingProfile = findByEmail(email);
    if (existingProfile.isEmpty())
      return false;

    delete(existingProfile.get());
    return true;
  }

}
