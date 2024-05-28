// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

public interface PasswordsStrengthAnalysisOutput {

  Number strength();

  boolean isStrong();

  PasswordsStrengthImprovementOutput asImprovementOutput();

}