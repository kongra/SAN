// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import edu.san.authentication.control.ProfileKind;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
class B2C extends AbstractProfileEntity {

  @NotNull
  @NotBlank
  @Column(length = 64)
  String address;

  B2C(String email,
      String firstName,
      String lastName,
      ProfileKind kind,
      String address) {
    super(email, firstName, lastName, kind);
    this.address = address;
  }

}
