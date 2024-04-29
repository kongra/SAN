// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import jakarta.validation.constraints.NotNull;

public class IsStrongPasswordOutput {

  @NotNull
  public Boolean isStrong;

  public IsStrongPasswordOutput() {}

  public IsStrongPasswordOutput(boolean isStrong) {
    this.isStrong = isStrong;
  }

}
