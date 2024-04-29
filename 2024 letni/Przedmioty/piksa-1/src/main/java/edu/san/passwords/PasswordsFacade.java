// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import java.util.Objects;

import telsos.string.NonBlank;

public final class PasswordsFacade {

  private final PasswordsStrengthAnalyzer passwordStrengthAnalyzer;

  public PasswordsFacade(PasswordsStrengthAnalyzer passwordStrengthAnalyzer) {
    this.passwordStrengthAnalyzer = Objects
        .requireNonNull(passwordStrengthAnalyzer);
  }

  public boolean isStrong(NonBlank password) {
    return passwordStrengthAnalyzer.analyze(password).isStrong();
  }

}
