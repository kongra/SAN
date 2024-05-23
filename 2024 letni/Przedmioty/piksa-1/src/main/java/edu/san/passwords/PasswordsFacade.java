// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import telsos.logging.Log;
import telsos.logging.Logs;

public abstract class PasswordsFacade {

  private static final Log LOG = Logs.forClass().create(PasswordsFacade.class);

  protected abstract PasswordsStrengthAnalyzer getPasswordsStrengthAnalyzer();

  public final boolean isStrong(IsStrongPasswordQuery query) {
    final var nonBlankPassword = query.nonBlankPassword();

    LOG.info("isStrong password=%s", nonBlankPassword);
    return getPasswordsStrengthAnalyzer()
        .analyze(nonBlankPassword)
        .isStrong();
  }
}
