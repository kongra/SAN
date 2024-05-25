// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import telsos.logging.Log;
import telsos.logging.Logs;

public interface PasswordsFacade {

  static final Log LOG = Logs.forClass().create(PasswordsFacade.class);

  PasswordsStrengthAnalyzer getPasswordsStrengthAnalyzer();

  default boolean isStrong(IsStrongPasswordQuery query) {
    final var nonBlankPassword = query.nonBlankPassword();
    // LOG.info(serviceProcessCtx, "isStrong password=%s", nonBlankPassword);
    return getPasswordsStrengthAnalyzer()
        .analyze(nonBlankPassword)
        .isStrong();
  }
}
