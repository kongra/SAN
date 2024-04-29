// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import jakarta.validation.constraints.NotBlank;
import telsos.string.NonBlank;

public class NonBlankPasswordInput {

  @NotBlank
  public String nonBlankPassword;

  public NonBlank asNonBlank() {
    return NonBlank.of(nonBlankPassword)
        .orElseThrow(IllegalArgumentException::new);

  }

}
