// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import edu.san.passwords.IsStrongPasswordQuery;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import telsos.strings.NonBlank;

public class NonBlankPasswordQuery implements IsStrongPasswordQuery {

  @NotNull
  @NotBlank
  public String password;

  public NonBlankPasswordQuery() {}

  public NonBlankPasswordQuery(String password) {
    this.password = password;
  }

  @Override
  public NonBlank nonBlankPassword() {
    return NonBlank.of(password).orElseThrow(IllegalArgumentException::new);
  }
}
