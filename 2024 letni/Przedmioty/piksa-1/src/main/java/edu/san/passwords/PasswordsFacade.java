// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PasswordsFacade {

  private static final Logger LOG = Logger
      .getLogger(PasswordsFacade.class.getName());

  private final PasswordsStrengthAnalyzer passwordStrengthAnalyzer;

  public PasswordsFacade(PasswordsStrengthAnalyzer passwordStrengthAnalyzer) {
    this.passwordStrengthAnalyzer = Objects
        .requireNonNull(passwordStrengthAnalyzer);
  }

  public boolean isStrong(IsStrongPasswordQuery query) {
    LOG.log(Level.INFO, "isStrong is called");
    return passwordStrengthAnalyzer.analyze(query.nonBlankPassword())
        .isStrong();
  }

}
