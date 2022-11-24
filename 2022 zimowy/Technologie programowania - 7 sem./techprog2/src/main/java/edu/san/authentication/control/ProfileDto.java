package edu.san.authentication.control;

import java.util.Objects;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import telsos.string.Email;
import telsos.string.NonBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ProfileDto {

  private UUID uuid;

  private short version;

  private Email email;

  private NonBlank firstName;

  private NonBlank lastName;

  private ProfileKind kind;

  public UUID getUuid() {
    return Objects.requireNonNull(uuid);
  }

  public short getVersion() {
    return version;
  }

  public Email getEmail() {
    return Objects.requireNonNull(email);
  }

  public NonBlank getFirstName() {
    return Objects.requireNonNull(firstName);
  }

  public NonBlank getLastName() {
    return Objects.requireNonNull(lastName);
  }

  public ProfileKind getProfileKind() {
    return Objects.requireNonNull(kind);
  }

}
