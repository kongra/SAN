// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.san.profiles.Password;
import edu.san.profiles.ProfileValuesFactory;
import edu.san.profiles.Username;

class ProfileValuesFactoryImpl implements ProfileValuesFactory {

  private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{3,20}$";

  private static final Pattern usernamePattern = Pattern
      .compile(USERNAME_PATTERN);

  private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9_-]{3,20}$";
      // "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,}$";

  private static final Pattern passwordPattern = Pattern
      .compile(PASSWORD_PATTERN);

  private static boolean isValidUsername(String username) {
    Matcher matcher = usernamePattern.matcher(username);
    return matcher.matches();
  }

  private static boolean isValidPassword(String password) {
    Matcher matcher = passwordPattern.matcher(password);
    return matcher.matches();
  }

  public static String hashPassword(String password) {
    try {
      // Create MessageDigest instance for SHA-256
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      // Add password bytes to digest
      md.update(password.getBytes());
      // Get the hash's bytes
      byte[] bytes = md.digest();
      // Convert byte[] to hexadecimal format
      StringBuilder sb = new StringBuilder();
      for (byte b : bytes) {
        sb.append(String.format("%02x", b));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Optional<Username> createUsername(String username) {
    Objects.requireNonNull(username);
    if (isValidUsername(username))
      return Optional.of(new UsernameImpl(username));

    return Optional.empty();
  }

  @Override
  public Optional<Password> createPassword(String password) {
    Objects.requireNonNull(password);
    if (isValidPassword(password))
      return Optional.of(new PasswordImpl(hashPassword(password)));

    return Optional.empty();
  }

}
