package edu.san.authentication.core;

import java.util.Objects;

public record ProfileId(Long value) {

  public ProfileId {
    Objects.requireNonNull(value);
  }

}
