// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import edu.san.authentication.boundary.validation.FirstName;
import edu.san.authentication.boundary.validation.LastName;
import edu.san.authentication.control.ProfileKind;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
class B2CEntity extends AbstractProfileEntity {

  @FirstName
  @NotNull
  @Column(length = 64)
  String firstName;

  @LastName
  @NotNull
  @Column(length = 128)
  String lastName;

  B2CEntity(String email,
      String firstName,
      String lastName,
      ProfileKind kind,
      String address) {
    super(email, kind, address);
    this.firstName = firstName;
    this.lastName = lastName;
  }

}
