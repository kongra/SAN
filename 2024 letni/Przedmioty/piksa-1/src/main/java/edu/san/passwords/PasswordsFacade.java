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
    return passwordStrengthAnalyzer.isMinimumEntropyMet(password);
  }

//  public PasswordStrengthAnalysisResult analyzePasswordStrength(
//      NonBlank password) {
//    // TODO: implement
//    throw new UnsupportedOperationException("Method not yet implemented");
//  }

}
