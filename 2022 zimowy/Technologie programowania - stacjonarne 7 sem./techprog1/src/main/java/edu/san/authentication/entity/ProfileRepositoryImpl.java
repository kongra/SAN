package edu.san.authentication.entity;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import edu.san.authentication.control.ProfileDto;
import edu.san.authentication.control.ProfileId;
import edu.san.authentication.control.ProfileRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import telsos.string.Email;
import telsos.string.NonBlank;

@ApplicationScoped
@Transactional
class ProfileRepositoryImpl
    implements PanacheRepository<ProfileEntity>, ProfileRepository {

  @Override
  public Optional<ProfileId> signUp(Email email, NonBlank firstName, NonBlank lastName) {
    final Optional<ProfileId> nothing = Optional.empty();
    return findByEmail(email).map(profile -> nothing).orElseGet(() -> {
      final var profileEntity = new ProfileEntity(
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
  public Optional<ProfileId> findProfileIdByEmail(Email email) {
    return findByEmail(email).map(ProfileRepositoryImpl::createProfileId);
  }

  @Override
  public Optional<ProfileDto> findProfileDtoByEmail(Email email) {
    return findByEmail(email).map(profileEntity -> new ProfileDto(
        profileEntity.id,
        profileEntity.version,
        profileEntity.email,
        profileEntity.firstName,
        profileEntity.lastName));
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
