// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

public sealed interface PasswordStrengthAnalysisResult {

  public record Success(PasswordStrengthSuggestion suggestion)
      implements PasswordStrengthAnalysisResult {}

  public final class Timeout implements PasswordStrengthAnalysisResult {

    public static final Timeout INSTANCE = new Timeout();

    private Timeout() {}
  }

}
