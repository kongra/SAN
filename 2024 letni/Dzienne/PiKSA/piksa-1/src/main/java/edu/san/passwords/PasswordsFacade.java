// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import telsos.string.NonBlank;

public interface PasswordsFacade {

  boolean isStrong(NonBlank password);

  PasswordStrengthSuggestion getPasswordStrengthSuggestion(NonBlank password);

}
