// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import java.util.Optional;

import telsos.math.newtype.PosLong;
import telsos.string.NonBlank;

public interface PasswordsStrengthAnalyzer {

  public interface Output {

    Number strength();

    boolean isStrong();

  }

  Output analyze(NonBlank password);

  public interface ImprovementOutput extends Output {

    /**
     * @return Optional.empty only when password is strong and no suggestion
     *         needed
     */
    Optional<NonBlank> strongerPasswordMask();
  }

  /**
   * @param passwordStrengthAnalysisInput
   * @param timeoutMillis
   * @return Optional.empty only when timed-out
   */
  Optional<ImprovementOutput> suggestImprovementIfNeeded(
      NonBlank password, PosLong timeoutMillis);

}
