// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import jakarta.validation.constraints.NotNull;

public class IsStrongPasswordResult {

  @NotNull
  public Boolean isStrong;

  public IsStrongPasswordResult() {}

  public IsStrongPasswordResult(boolean isStrong) {
    this.isStrong = isStrong;
  }

}
