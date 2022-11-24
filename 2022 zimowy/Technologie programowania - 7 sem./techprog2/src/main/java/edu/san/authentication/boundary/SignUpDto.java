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
public class SignUpDto { // Data-Transfer-Object

  @Email
  @NotNull
  private String email;

  @FirstName
  private String firstName;

  @LastName
  private String lastName;

  @NotNull
  @NotBlank
  private String profileKind;

}
