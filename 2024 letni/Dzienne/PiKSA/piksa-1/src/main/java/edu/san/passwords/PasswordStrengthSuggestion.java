// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import java.util.Optional;

import telsos.string.NonBlank;

public interface PasswordStrengthSuggestion {

  Number strength();

  boolean isStrong();

  Optional<NonBlank> strongerPassword();

  Optional<NonBlank> strongerPasswordMask();

}
