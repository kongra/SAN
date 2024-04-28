// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import telsos.string.NonBlank;

public interface PasswordsStrengthAnalyzer {

  boolean isMinimumEntropyMet(NonBlank password);

}
