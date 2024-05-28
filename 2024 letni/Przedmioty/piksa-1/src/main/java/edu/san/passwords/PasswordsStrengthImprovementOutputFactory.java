// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import telsos.strings.NonBlank;

public interface PasswordsStrengthImprovementOutputFactory {

  PasswordsStrengthImprovementOutput create(Number strength, boolean isStrong,
      NonBlank passwordMask);

  PasswordsStrengthImprovementOutput create(Number strength, boolean isStrong);

}