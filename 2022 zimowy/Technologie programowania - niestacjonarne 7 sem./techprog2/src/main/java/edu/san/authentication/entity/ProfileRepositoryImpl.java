package edu.san.authentication.entity;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import edu.san.authentication.control.ProfileDto;
import edu.san.authentication.control.ProfileId;
import edu.san.authentication.control.ProfileRepository;
import edu.san.hexagonal.Adapter;
import edu.san.hexagonal.PortOrAdapterType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import telsos.string.Email;
import telsos.string.NonBlank;

@ApplicationScoped
@Adapter(PortOrAdapterType.OUTBOUND)
class ProfileRepositoryImpl
    implements PanacheRepository<ProfileEntity>, ProfileRepository {

  @Override
  @Transactional
  public ProfileId createProfile(
      Email email,
      NonBlank firstName,
      NonBlank lastName) {

    final var profileEntity = new ProfileEntity(
        email.value(),
        firstName.value(),
        lastName.value());

    persist(profileEntity);
    return new ProfileId(profileEntity.id);
  }

  private static ProfileId createProfileId(ProfileEntity profile) {
    return new ProfileId(profile.id);
  }

  @Override
  @Transactional
  public Optional<ProfileId> findProfileIdByEmail(Email email) {
    return findProfileEntityByEmail(email)
        .map(ProfileRepositoryImpl::createProfileId);
  }

  @Override
  @Transactional
  public Optional<ProfileDto> findProfileDtoByEmail(Email email) {
    return findProfileEntityByEmail(email)
        .map(profileEntity -> new ProfileDto(
            profileEntity.id,
            profileEntity.version,
            profileEntity.email,
            profileEntity.firstName,
            profileEntity.lastName));
  }

  @Transactional
  Optional<ProfileEntity> findProfileEntityByEmail(Email email) {
    return find("email = ?1", email.value()).firstResultOptional();
  }

  @Override
  @Transactional
  public Optional<ProfileId> deleteProfileByEmail(Email email) {
    final var optionalProfile = findProfileEntityByEmail(email);
    if (optionalProfile.isEmpty())
      return Optional.empty();

    final var profile = optionalProfile.get();
    delete(profile);
    return Optional.of(createProfileId(profile));
  }

}
