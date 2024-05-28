// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import java.util.Optional;

import telsos.strings.NonBlank;

public interface PasswordsStrengthImprovementOutput
    extends PasswordsStrengthAnalysisOutput {

  /**
   * @return Optional.empty only when password is strong and no suggestion was
   *         needed
   */
  Optional<NonBlank> strongerPasswordMask();
}