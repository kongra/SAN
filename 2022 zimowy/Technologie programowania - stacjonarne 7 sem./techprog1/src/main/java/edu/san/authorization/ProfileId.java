package edu.san.authorization;

import java.util.Objects;

public record ProfileId(Long value) {

  public ProfileId {
    Objects.requireNonNull(value);
  }

}
