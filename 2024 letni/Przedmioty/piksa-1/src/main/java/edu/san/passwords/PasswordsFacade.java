// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class PasswordsFacade {

  private static final Logger LOG = Logger.getLogger(PasswordsFacade.class.getName());

  protected abstract PasswordsStrengthAnalyzer getPasswordStrengthAnalyzer();

  public boolean isStrong(IsStrongPasswordQuery query) {
    LOG.log(Level.INFO, "isStrong is called");
    return getPasswordStrengthAnalyzer()
        .analyze(query.nonBlankPassword())
        .isStrong();
  }
}
