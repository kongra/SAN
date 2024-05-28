// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import telsos.logging.Log;
import telsos.logging.Logs;

public interface PasswordsFacade {

  Log LOG = Logs.forClass().getLog(PasswordsFacade.class);

  PasswordsStrengthAnalyzer getPasswordsStrengthAnalyzer();

  default boolean isStrongPassword(IsStrongPasswordQuery query) {
    final var nonBlankPassword = query.nonBlankPassword();
    // LOG.info(serviceProcessCtx, "isStrongPassword password=%s",
    // nonBlankPassword);
    return getPasswordsStrengthAnalyzer()
        .analyze(nonBlankPassword)
        .isStrong();
  }
}
