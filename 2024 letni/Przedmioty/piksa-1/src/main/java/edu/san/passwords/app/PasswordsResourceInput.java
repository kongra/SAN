// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import jakarta.validation.constraints.NotBlank;

public class PasswordsResourceInput {

  @NotBlank
  public String password;

}
