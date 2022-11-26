// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.boundary;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import edu.san.authentication.boundary.validation.FirstName;
import edu.san.authentication.boundary.validation.LastName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

  @Email
  @NotNull
  private String email;

  @FirstName
  @NotNull
  private String firstName;

  @LastName
  @NotNull
  private String lastName;

  @NotBlank
  @NotNull
  private String profileKind;

}
