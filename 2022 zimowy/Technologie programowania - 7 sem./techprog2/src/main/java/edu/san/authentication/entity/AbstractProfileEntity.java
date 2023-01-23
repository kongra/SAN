// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import edu.san.authentication.control.ProfileKind;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "profile_DTYPE", discriminatorType = DiscriminatorType.INTEGER)
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("java:S1694")
abstract class AbstractProfileEntity {

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
  @Column(unique = true, nullable = false, length = 128)
  String email;

  @NotNull
  @Enumerated(EnumType.ORDINAL)
  @Column(nullable = false, columnDefinition = "int2")
  ProfileKind kind;

  @NotNull
  @NotBlank
  @Column(length = 64)
  String address;

  @OneToMany(mappedBy = "profile")
  @Valid
  Set<CommentEntity> comments = new HashSet<>();

  AbstractProfileEntity(
      @Email @NotNull String email,
      @NotNull ProfileKind kind,
      @NotNull @NotBlank String address) {
    this.email = email;
    this.kind = kind;
    this.address = address;
  }

  @Override
  public final int hashCode() {
    return uuid.hashCode();
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj instanceof final AbstractProfileEntity other)
      return uuid.equals(other.uuid);
    return false;
  }

  void setUuid(UUID uuid) {
    this.uuid = Objects.requireNonNull(uuid);
  }

  private static final String PROFILE_SEQUENCE = "profile_sequence";

}
