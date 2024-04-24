// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import java.util.Objects;

import edu.san.profiles.Password;

record PasswordImpl(String value) implements Password {

  PasswordImpl {
    Objects.requireNonNull(value);
  }

}
