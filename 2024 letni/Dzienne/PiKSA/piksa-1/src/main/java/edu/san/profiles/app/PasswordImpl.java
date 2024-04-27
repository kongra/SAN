// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import java.util.Objects;

import edu.san.profiles.Password;
import telsos.string.NonBlank;

record PasswordImpl(NonBlank value) implements Password {

  PasswordImpl {
    Objects.requireNonNull(value);
  }

}
