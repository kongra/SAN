package edu.san.authentication.control;

import java.util.Objects;
import java.util.UUID;

import lombok.Data;

@Data
public final class ProfileDto {

  private final UUID uuid;

  private final short version;

  private final String email;

  private final String firstName;

  private final String lastName;

  private final ProfileKind profileKind;

  public ProfileDto(UUID uuid, short version, String email, String firstName,
      String lastName, ProfileKind profileKind) {
    this.uuid = Objects.requireNonNull(uuid);
    this.version = version;
    this.email = Objects.requireNonNull(email);
    this.firstName = Objects.requireNonNull(firstName);
    this.lastName = Objects.requireNonNull(lastName);
    this.profileKind = Objects.requireNonNull(profileKind);
  }

}
