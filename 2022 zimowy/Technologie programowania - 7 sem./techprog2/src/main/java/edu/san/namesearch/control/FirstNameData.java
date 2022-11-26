// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.namesearch.control;

import java.util.Objects;

import edu.san.authentication.control.FirstName;
import telsos.math.newtype.NatLong;

public record FirstNameData(FirstName firstName, NatLong count, Gender gender) {
  public FirstNameData {
    Objects.requireNonNull(firstName);
    Objects.requireNonNull(count);
    Objects.requireNonNull(gender);
  }
}
