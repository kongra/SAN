package edu.san.authentication.infrastructure;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile")
@NoArgsConstructor
class ProfileEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @NotNull
  @NotBlank
  @Column(unique = true)
  String uuid = UUID.randomUUID().toString();

  @Email
  @NotNull
  @Column(unique = true)
  String email;

  @NotNull
  @NotBlank
  String firstName;

  @NotNull
  @NotBlank
  String lastName;

  public ProfileEntity(
      @Email @NotNull String email,
      @NotNull @NotBlank String firstName,
      @NotNull @NotBlank String lastName) {

    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public final int hashCode() {
    return uuid.hashCode();
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj instanceof final ProfileEntity other)
      return uuid.equals(other.uuid);
    return false;
  }

  public final void setUuid(String uuid) {
    Objects.requireNonNull(uuid);
    if (uuid.isBlank())
      throw new IllegalArgumentException();
    this.uuid = uuid;
  }

}
