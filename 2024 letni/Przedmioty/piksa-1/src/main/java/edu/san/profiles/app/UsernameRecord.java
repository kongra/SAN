// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import java.util.Objects;

import edu.san.profiles.Username;
import telsos.string.NonBlank;

record UsernameRecord(NonBlank value) implements Username {

  UsernameRecord {
    Objects.requireNonNull(value);
  }

}
