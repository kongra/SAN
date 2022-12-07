// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.boundary;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class B2BSignUpDto {

  @Email
  @NotNull
  private String email;

  @NotBlank
  @NotNull
  private String profileKind;

  @NotBlank
  @NotNull
  private String address;

  @NotBlank
  @NotNull
  private String regon;

}
