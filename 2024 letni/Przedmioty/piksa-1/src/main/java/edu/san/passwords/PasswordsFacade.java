// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class PasswordsFacade {

  private static final Logger LOG = Logger.getLogger(PasswordsFacade.class.getName());

  protected abstract PasswordsStrengthAnalyzer passwordStrengthAnalyzer();

  public boolean isStrong(IsStrongPasswordQuery query) {
    LOG.log(Level.INFO, "isStrong is called");
    return passwordStrengthAnalyzer().analyze(query.nonBlankPassword())
        .isStrong();
  }

}
