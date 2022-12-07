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
public class B2BEntity extends AbstractProfileEntity {

  @NotNull
  @NotBlank
  @Column(length = 64)
  String regon;

  B2BEntity(String email,
      ProfileKind kind,
      String address,
      String regon) {
    super(email, kind, address);
    this.regon = regon;
  }

}
