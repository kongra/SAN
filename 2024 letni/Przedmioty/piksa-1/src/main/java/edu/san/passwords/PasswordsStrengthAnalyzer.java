// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import java.util.Optional;

import telsos.strings.NonBlank;

public abstract class PasswordsStrengthAnalyzer {

  public interface AnalysisOutput {

    Number strength();

    boolean isStrong();

  }

  public abstract AnalysisOutput analyze(NonBlank password);

  public interface ImprovementOutput extends AnalysisOutput {

    /**
     * @return Optional.empty only when password is strong and no suggestion was
     *         needed
     */
    Optional<NonBlank> strongerPasswordMask();
  }

  public interface ImprovementOutputFactory {

    ImprovementOutput create(Number strength, boolean isStrong,
        NonBlank passwordMask);

    ImprovementOutput create(Number strength, boolean isStrong);

  }

  protected abstract ImprovementOutputFactory getImprovementOutputFactory();

  public ImprovementOutput suggestImprovementIfNeeded(NonBlank password) {
    final var output = analyze(password);

    if (output.isStrong())
      return getImprovementOutputFactory().create(output.strength(),
          output.isStrong());

    // TODO: implement password improvement suggestion
    return null;
  }

}
