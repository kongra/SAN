// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import java.util.Optional;

import telsos.string.NonBlank;

public interface PasswordsStrengthAnalyzer {

  Output analyze(NonBlank password);

  /**
   * @param passwordStrengthAnalysisInput
   * @return Optional.empty only when timed-out
   */
  Optional<ImprovementOutput> suggestImprovementIfNeeded(
      NonBlank password);

  public interface Output {

    Number strength();

    boolean isStrong();

  }

  public interface ImprovementOutput
      extends Output {

    /**
     * @return Optional.empty only when password is strong and no suggestion
     *         needed
     */
    Optional<NonBlank> strongerPassword();

    /**
     * @return Optional.empty only when password is strong and no suggestion
     *         needed
     */
    Optional<NonBlank> strongerPasswordMask();
  }

}
