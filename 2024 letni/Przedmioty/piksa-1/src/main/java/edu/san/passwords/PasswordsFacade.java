// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import telsos.logging.Log;

public abstract class PasswordsFacade {

  protected abstract PasswordsStrengthAnalyzer getPasswordStrengthAnalyzer();

  protected abstract Log log();

  public boolean isStrong(IsStrongPasswordQuery query) {
    final var nonBlankPassword = query.nonBlankPassword();

    log().info("isStrong password=%s", nonBlankPassword);
    return getPasswordStrengthAnalyzer()
        .analyze(nonBlankPassword)
        .isStrong();
  }
}
