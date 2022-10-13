package edu.san.authentication;

import javax.validation.constraints.Email;
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
  private String firstName;

  @NotNull
  private String lastName;

}
