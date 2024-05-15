// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import java.util.Objects;

import edu.san.passwords.PasswordsFacade;
import edu.san.passwords.PasswordsStrengthAnalyzer;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class PasswordFacadeImpl extends PasswordsFacade {

  private final PasswordsStrengthAnalyzer passwordsStrengthAnalyzer;

  PasswordFacadeImpl(PasswordsStrengthAnalyzer passwordsStrengthAnalyzer) {
    this.passwordsStrengthAnalyzer = Objects
        .requireNonNull(passwordsStrengthAnalyzer);
  }

  @Override
  protected final PasswordsStrengthAnalyzer passwordStrengthAnalyzer() {
    return passwordsStrengthAnalyzer;
  }

}
