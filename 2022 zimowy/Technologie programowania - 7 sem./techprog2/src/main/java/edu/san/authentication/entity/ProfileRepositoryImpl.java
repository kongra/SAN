// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.entity;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import edu.san.authentication.control.B2CDto;
import edu.san.authentication.control.FirstName;
import edu.san.authentication.control.LastName;
import edu.san.authentication.control.ProfileId;
import edu.san.authentication.control.ProfileKind;
import edu.san.authentication.control.ProfileRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import telsos.architecture.hexagonal.annotations.Adapter;
import telsos.architecture.hexagonal.annotations.AdapterType;
import telsos.string.Email;
import telsos.string.NonBlank;

@Adapter(AdapterType.SECONDARY)
@ApplicationScoped
class ProfileRepositoryImpl
    implements PanacheRepository<AbstractProfileEntity>, ProfileRepository {

  @Override
  @Transactional
  public ProfileId createB2C(
      Email email,
      FirstName firstName,
      LastName lastName,
      ProfileKind profileKind,
      NonBlank address) {

    final var newB2CEntity = new B2CEntity(
        email.value(),
        firstName.value(),
        lastName.value(),
        profileKind,
        address.value());

    persist(newB2CEntity);
    return new ProfileId(newB2CEntity.uuid);
  }

  @Override
  @Transactional
  public ProfileId createB2B(
      Email email,
      ProfileKind profileKind,
      NonBlank address,
      NonBlank regon) {

    final var newB2BEntity = new B2BEntity(
        email.value(),
        profileKind,
        address.value(),
        regon.value());

    persist(newB2BEntity);
    return new ProfileId(newB2BEntity.uuid);
  }

  @Override
  public Optional<ProfileId> findProfileIdByEmail(Email email) {
    return findProfileEntityByEmail(email)
        .map(profileEntity -> new ProfileId(profileEntity.uuid));
  }

  @Override
  public Optional<B2CDto> findProfileDtoByEmail(Email email) {
    return findB2CEntityByEmail(email)
        .map(ProfileRepositoryImpl::b2CEntity2ProfileDto);
  }

  Optional<AbstractProfileEntity> findProfileEntityByEmail(Email email) {
    return find("email = ?1", email.value()).firstResultOptional();
  }

  Optional<B2CEntity> findB2CEntityByEmail(Email email) {
    return find("email = ?1", email.value()).firstResultOptional();
  }

  @Override
  public Optional<ProfileId> deleteProfileByEmail(Email email) {
    final var optionalProfileEntity = findProfileEntityByEmail(email);
    if (optionalProfileEntity.isEmpty())
      return Optional.empty();

    final var profileEntity = optionalProfileEntity.get();
    delete(profileEntity);
    return Optional.of(new ProfileId(profileEntity.uuid));
  }

  private static B2CDto b2CEntity2ProfileDto(
      B2CEntity b2CEntity) {
    return new B2CDto(
        b2CEntity.uuid,
        b2CEntity.version,
        Email.of(b2CEntity.email).orElseThrow(),
        FirstName.of(b2CEntity.firstName).orElseThrow(),
        LastName.of(b2CEntity.lastName).orElseThrow(),
        b2CEntity.kind);
  }
}
