// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import edu.san.monad.control.Either;

public final class Numbers {

  private static String forInputString(String s, int radix) {
    return "For input string: \"" + s + "\"" +
        (radix == 10 ? "" : " under radix " + radix);
  }

  public static Either<String, Long> parseLong(String s, int radix) {
    if (s == null)
      return new Either.Left<>("Cannot parse null string");

    if (radix < Character.MIN_RADIX)
      return new Either.Left<>("radix " + radix +
          " less than Character.MIN_RADIX");
    if (radix > Character.MAX_RADIX)
      return new Either.Left<>("radix " + radix +
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
          return new Either.Left<>(forInputString(s, radix));

        if (len == 1)
          return new Either.Left<>(forInputString(s, radix));
        i++;
      }
      final var multmin = limit / radix;
      var result = 0L;
      while (i < len) {
        // Accumulating negatively avoids surprises near MAX_VALUE
        final var digit = Character.digit(s.charAt(i), radix);
        i++;

        if (digit < 0 || result < multmin)
          return new Either.Left<>(forInputString(s, radix));
        result *= radix;
        if (result < limit + digit)
          return new Either.Left<>(forInputString(s, radix));
        result -= digit;
      }
      return new Either.Right<>(negative ? result : -result);
    }
    return new Either.Left<>(forInputString(s, radix));
  }

  private Numbers() {}

}
