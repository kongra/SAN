// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.security.SecureRandom;
import java.util.Random;

public class StatusMonitor {

  private final Random random = new SecureRandom();

  public Either<ErrorSeverity, Integer> getCurrentValue() {
    final var n = random.nextInt();
    return n < 10 ? Either.right(n) : Either.left(ErrorSeverity.HIGH);
  }

  // int | ErrorSeverity

}
