// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.entity;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
class CommentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = COMMENT_SEQUENCE)
  @SequenceGenerator(name = COMMENT_SEQUENCE, sequenceName = COMMENT_SEQUENCE, allocationSize = 100)
  Long id;

  @Version
  @Column(nullable = false, columnDefinition = "int2")
  short version;

  @NotNull
  @Column(unique = true, nullable = false)
  UUID uuid = UUID.randomUUID();

  @NotNull
  @NotBlank
  @Column(length = 64)
  String title;

  @NotNull
  @NotBlank
  @Column(length = 4096)
  String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "profile_id")
  @Valid
  AbstractProfileEntity profile;

  CommentEntity(
      @NotNull @NotBlank String title,
      @NotNull @NotBlank String content,
      @Valid AbstractProfileEntity profile) {
    super();
    this.title = title;
    this.content = content;
    this.profile = profile;
  }

  @Override
  public final int hashCode() {
    return uuid.hashCode();
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj instanceof final CommentEntity other)
      return uuid.equals(other.uuid);
    return false;
  }

  void setUuid(UUID uuid) {
    this.uuid = Objects.requireNonNull(uuid);
  }

  private static final String COMMENT_SEQUENCE = "comment_sequence";

}
