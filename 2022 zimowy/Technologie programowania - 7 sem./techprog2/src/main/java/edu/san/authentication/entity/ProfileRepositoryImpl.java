// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.entity;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import edu.san.authentication.control.FirstName;
import edu.san.authentication.control.LastName;
import edu.san.authentication.control.ProfileDto;
import edu.san.authentication.control.ProfileId;
import edu.san.authentication.control.ProfileKind;
import edu.san.authentication.control.ProfileRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import telsos.architecture.hexagonal.annotations.Adapter;
import telsos.architecture.hexagonal.annotations.AdapterType;
import telsos.string.Email;

@Adapter(AdapterType.SECONDARY)
@ApplicationScoped
class ProfileRepositoryImpl
    implements PanacheRepository<ProfileEntity>, ProfileRepository {

  @Override
  @Transactional
  public ProfileId createProfile(
      Email email,
      FirstName firstName,
      LastName lastName,
      ProfileKind profileKind) {

    final var profileEntity = new ProfileEntity(
        email.value(),
        firstName.value(),
        lastName.value(),
        profileKind);

    persist(profileEntity);
    return new ProfileId(profileEntity.uuid);
  }

  @Override
  @Transactional
  public Optional<ProfileId> findProfileIdByEmail(Email email) {
    return findProfileEntityByEmail(email)
        .map(profileEntity -> new ProfileId(profileEntity.uuid));
  }

  @Override
  @Transactional
  public Optional<ProfileDto> findProfileDtoByEmail(Email email) {
    return findProfileEntityByEmail(email)
        .map(ProfileRepositoryImpl::profileEntity2ProfileDto);
  }

  @Transactional
  Optional<ProfileEntity> findProfileEntityByEmail(Email email) {
    return find("email = ?1", email.value()).firstResultOptional();
  }

  @Override
  @Transactional
  public Optional<ProfileId> deleteProfileByEmail(Email email) {
    final var optionalProfileEntity = findProfileEntityByEmail(email);
    if (optionalProfileEntity.isEmpty())
      return Optional.empty();

    final var profileEntity = optionalProfileEntity.get();
    delete(profileEntity);
    return Optional.of(new ProfileId(profileEntity.uuid));
  }

  private static ProfileDto profileEntity2ProfileDto(
      ProfileEntity profileEntity) {
    return new ProfileDto(
        profileEntity.uuid,
        profileEntity.version,
        Email.of(profileEntity.email).orElseThrow(),
        FirstName.of(profileEntity.firstName).orElseThrow(),
        LastName.of(profileEntity.lastName).orElseThrow(),
        profileEntity.kind);
  }
}
