// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import edu.san.monad.control.Either;

public final class Longs {

  private static String forInputString(String s, int radix) {
    return "For input string: \"" + s + "\"" +
        (radix == 10 ? "" : " under radix " + radix);
  }

  @SuppressWarnings("java:S3776")
  public static Either<String, Long> parseLong(String s, int radix) {
    if (s == null)
      return Either.left("Cannot parse null string");

    if (radix < Character.MIN_RADIX)
      return Either.left("radix " + radix +
          " less than Character.MIN_RADIX");
    if (radix > Character.MAX_RADIX)
      return Either.left("radix " + radix +
          " greater than Character.MAX_RADIX");

    var negative = false;
    var i = 0;
    final var len = s.length();
    var limit = -Long.MAX_VALUE;

    if (len > 0) {
      final var firstChar = s.charAt(0);
      if (firstChar < '0') { // Possible leading "+" or "-"
        if (firstChar == '-') {
          negative = true;
          limit = Long.MIN_VALUE;
        } else if (firstChar != '+')
          return Either.left(forInputString(s, radix));

        if (len == 1)
          return Either.left(forInputString(s, radix));
        i++;
      }
      final var multmin = limit / radix;
      var result = 0L;
      while (i < len) {
        // Accumulating negatively avoids surprises near MAX_VALUE
        final var digit = Character.digit(s.charAt(i), radix);
        i++;

        if (digit < 0 || result < multmin)
          return Either.left(forInputString(s, radix));
        result *= radix;
        if (result < limit + digit)
          return Either.left(forInputString(s, radix));
        result -= digit;
      }
      return Either.right(negative ? result : -result);
    }
    return Either.left(forInputString(s, radix));
  }

  private Longs() {}

}
