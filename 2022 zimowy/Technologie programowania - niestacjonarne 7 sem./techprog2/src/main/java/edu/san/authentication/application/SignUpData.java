package edu.san.authentication.application;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpData {

  @Email
  @NotNull
  private String email;

  @NotNull
  @NotBlank
  private String firstName;

  @NotNull
  @NotBlank
  private String lastName;

}
