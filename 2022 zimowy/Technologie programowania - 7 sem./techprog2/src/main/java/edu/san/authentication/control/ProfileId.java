// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.control;

import java.util.Objects;
import java.util.UUID;

public record ProfileId(UUID value) {

  public ProfileId {
    Objects.requireNonNull(value);
  }

}
