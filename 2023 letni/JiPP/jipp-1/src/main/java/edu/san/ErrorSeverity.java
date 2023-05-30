// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.Comparator;

public enum ErrorSeverity {

  LOW(1), HIGH(2), MEDIUM(3);

  private final int value;

  ErrorSeverity(int value) {
    this.value = value;
  }

  private int value() {
    return value;
  }

  public static final Comparator<ErrorSeverity> COMPARATOR = Comparator
      .comparing(ErrorSeverity::value);
}
