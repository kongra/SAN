package edu.san.authentication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile")
@NoArgsConstructor
@AllArgsConstructor
class ProfileEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Email
  @NotNull
  @Column(unique = true)
  String email;

  @NotNull
  String firstName;

  @NotNull
  String lastName;

  @Override
  public final int hashCode() {
    return id.hashCode();
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj instanceof ProfileEntity other) {
      return this.id.longValue() == other.id.longValue();
    }
    return false;
  }

}
