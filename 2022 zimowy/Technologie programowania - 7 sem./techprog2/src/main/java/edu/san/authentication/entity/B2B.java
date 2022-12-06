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
public class B2B extends AbstractProfileEntity {

  @NotNull
  @NotBlank
  @Column(length = 64)
  String regon;
  
  B2B(String email,
      String firstName,
      String lastName,
      ProfileKind kind,
      String regon) {
    super(email, firstName, lastName, kind);
    this.regon = regon;
  }
  
}
