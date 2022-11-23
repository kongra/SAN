package edu.san.authentication.entity;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import edu.san.authentication.control.ProfileKind;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile")
@NoArgsConstructor
@AllArgsConstructor
class ProfileEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PROFILE_SEQUENCE)
  @SequenceGenerator(name = PROFILE_SEQUENCE, sequenceName = PROFILE_SEQUENCE, allocationSize = 100)
  Long id;

  @Version
  @Column(nullable = false, columnDefinition = "int2")
  short version;

  @NotNull
  @Column(unique = true, nullable = false)
  UUID uuid = UUID.randomUUID();

  @Email
  @NotNull
  @Column(unique = true, nullable = false)
  String email;

  @NotNull
  @NotBlank
  @Column(nullable = false)
  String firstName;

  @NotNull
  @NotBlank
  @Column(nullable = false)
  String lastName;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false, columnDefinition = "int2")
  ProfileKind profileKind;

  public ProfileEntity(
      String email,
      String firstName,
      String lastName,
      ProfileKind profileKind) {

    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.profileKind = profileKind;
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

  public void setUuid(UUID uuid) {
    this.uuid = Objects.requireNonNull(uuid);
  }

  private static final String PROFILE_SEQUENCE = "profile_sequence";

}
