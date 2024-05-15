// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import java.util.Objects;

import edu.san.passwords.PasswordsFacade;
import edu.san.passwords.PasswordsStrengthAnalyzer;
import jakarta.enterprise.context.ApplicationScoped;
import telsos.logging.Log;
import telsos.logging.Logs;

@ApplicationScoped
class PasswordFacadeImpl extends PasswordsFacade {

  private static final Log LOG = Logs.forClass()
      .create(PasswordFacadeImpl.class);

  private final PasswordsStrengthAnalyzer passwordsStrengthAnalyzer;

  PasswordFacadeImpl(PasswordsStrengthAnalyzer passwordsStrengthAnalyzer) {
    this.passwordsStrengthAnalyzer = Objects
        .requireNonNull(passwordsStrengthAnalyzer);
  }

  @Override
  protected final PasswordsStrengthAnalyzer getPasswordStrengthAnalyzer() {
    return passwordsStrengthAnalyzer;
  }

  @Override
  protected final Log log() {
    return LOG;
  }

}
