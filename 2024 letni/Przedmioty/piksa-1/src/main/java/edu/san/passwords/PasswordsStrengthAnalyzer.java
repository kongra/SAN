// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import telsos.logging.Log;
import telsos.logging.Logs;
import telsos.strings.NonBlank;

public abstract class PasswordsStrengthAnalyzer {

  public abstract PasswordsStrengthAnalysisOutput analyze(NonBlank password);

  public PasswordsStrengthImprovementOutput suggestImprovementIfNeeded(
      NonBlank password) {
    final var output = analyze(password);

    if (output.isStrong())
      return output.asImprovementOutput();

    // TODO: implement password improvement suggestion
    return null;
  }

  protected abstract PasswordsStrengthImprovementOutputFactory improvementOutputFactory();

  private static final Log LOG = Logs.forClass()
      .getLog(PasswordsStrengthAnalyzer.class);

  private static final String PASSWORD_SYMBOLS = """
      !"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~!@#$%^&!@#$%^&!@#$%^&!@#$%^&!@#$%^\
      &ABCDEFGHIJKLMNOPQRSTUWVXYZ1234567890\
      """;

}
