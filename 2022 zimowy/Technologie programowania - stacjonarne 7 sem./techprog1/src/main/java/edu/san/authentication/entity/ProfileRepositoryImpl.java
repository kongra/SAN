package edu.san.authentication.entity;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import edu.san.authentication.control.ProfileId;
import edu.san.authentication.control.ProfileRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import telsos.string.Email;
import telsos.string.NonBlank;

@ApplicationScoped
@Transactional
public class ProfileRepositoryImpl
    implements PanacheRepository<ProfileEntity>, ProfileRepository {

  @Override
  public Optional<ProfileId> signUp(Email email, NonBlank firstName, NonBlank lastName) {
    final var existingProfile = findByEmail(email);
    final Optional<ProfileId> nothing = Optional.empty();
    return existingProfile.map(profile -> nothing).orElseGet(() -> {
      final var profileEntity = new ProfileEntity(/* id */ null,
          email.value(),
          firstName.value(),
          lastName.value());

      persist(profileEntity);
      return Optional.of(new ProfileId(profileEntity.id));
    });
  }

  private static ProfileId createProfileId(ProfileEntity profile) {
    return new ProfileId(profile.id);
  }

  @Override
  public Optional<ProfileId> profileIdByEmail(Email email) {
    final var profileEntity = findByEmail(email);
    return profileEntity.map(ProfileRepositoryImpl::createProfileId);
  }

  Optional<ProfileEntity> findByEmail(Email email) {
    return find("email = ?1", email.value()).firstResultOptional();
  }

  @Override
  public Optional<ProfileId> deleteByEmail(Email email) {
    final var optionalProfile = findByEmail(email);
    if (optionalProfile.isEmpty())
      return Optional.empty();

    final var profile = optionalProfile.get();
    delete(profile);
    return Optional.of(createProfileId(profile));
  }

}
