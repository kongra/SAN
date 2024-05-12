// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import telsos.strings.NonBlank;

public class NonBlankPasswordInput {

  @NotNull
  @NotBlank
  public String nonBlankPassword;

  public NonBlankPasswordInput() {}

  public NonBlankPasswordInput(String nonBlankPassword) {
    this.nonBlankPassword = nonBlankPassword;
  }

  public NonBlank asNonBlank() {
    return NonBlank.of(nonBlankPassword)
        .orElseThrow(IllegalArgumentException::new);
  }

}
