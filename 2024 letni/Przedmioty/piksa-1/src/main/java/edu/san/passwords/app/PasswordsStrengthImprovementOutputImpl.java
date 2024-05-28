// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import java.util.Objects;
import java.util.Optional;

import edu.san.passwords.PasswordsStrengthImprovementOutput;
import telsos.strings.NonBlank;

record PasswordsStrengthImprovementOutputImpl(
    Number strength,
    boolean isStrong,
    NonBlank passwordMask) implements PasswordsStrengthImprovementOutput {

  PasswordsStrengthImprovementOutputImpl {
    Objects.requireNonNull(strength);
  }

  @Override
  public Optional<NonBlank> strongerPasswordMask() {
    return Optional.ofNullable(passwordMask);
  }

  @Override
  public PasswordsStrengthImprovementOutput asImprovementOutput() {
    return this;
  }

}
