// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import java.util.Objects;

import edu.san.profiles.Username;
import telsos.string.NonBlank;

record UsernameImpl(NonBlank value) implements Username {

  UsernameImpl {
    Objects.requireNonNull(value);
  }

}
