// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import java.util.Objects;

import edu.san.profiles.Password;
import telsos.string.NonBlank;

record PasswordRecord(NonBlank value) implements Password {

  PasswordRecord {
    Objects.requireNonNull(value);
  }

}
