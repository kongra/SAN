// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.control;

import java.util.Optional;
import java.util.regex.Pattern;

import telsos.newtype.Newtype;

public final class FirstName extends Newtype<String> {

  public static Optional<FirstName> of(String s) {
    return of(s, FirstName::isValid, FirstName::new);
  }

  public static boolean isValid(String s) {
    return null != s && PATTERN.matcher(s).matches();
  }

  private FirstName(String value) {
    super(value);
  }

  private static final Pattern PATTERN = Pattern
      .compile("^\\p{IsUppercase}\\p{IsLowercase}{1,63}$",
          Pattern.UNICODE_CHARACTER_CLASS);

}
