package edu.san.indexer.control;

import java.util.Objects;

import telsos.math.newtype.NatLong;
import telsos.string.NonBlank;

public record FirstName(NonBlank firstName, NatLong count, Gender gender) {
  public FirstName {
    Objects.requireNonNull(firstName);
    Objects.requireNonNull(count);
    Objects.requireNonNull(gender);
  }
}
