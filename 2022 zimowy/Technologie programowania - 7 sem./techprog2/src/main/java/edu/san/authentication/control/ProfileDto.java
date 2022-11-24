// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.control;

import java.util.Objects;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import telsos.string.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ProfileDto {

  private UUID uuid;

  private short version;

  private Email email;

  private FirstName firstName;

  private LastName lastName;

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

  public FirstName getFirstName() {
    return Objects.requireNonNull(firstName);
  }

  public LastName getLastName() {
    return Objects.requireNonNull(lastName);
  }

  public ProfileKind getProfileKind() {
    return Objects.requireNonNull(kind);
  }

}
