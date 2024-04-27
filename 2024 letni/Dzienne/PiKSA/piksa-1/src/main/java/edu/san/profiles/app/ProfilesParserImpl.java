// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import edu.san.profiles.Password;
import edu.san.profiles.ProfilesParser;
import edu.san.profiles.Username;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class ProfilesParserImpl implements ProfilesParser {

  private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{3,20}$";

  private static final Pattern usernamePattern = Pattern
      .compile(USERNAME_PATTERN);

  private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9_-]{3,20}$";
  // "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,}$";

  private static final Pattern passwordPattern = Pattern
      .compile(PASSWORD_PATTERN);

  private static boolean isValidUsername(String username) {
    final var matcher = usernamePattern.matcher(username);
    return matcher.matches();
  }

  private static boolean isValidPassword(String password) {
    final var matcher = passwordPattern.matcher(password);
    return matcher.matches();
  }

  public static String hashPassword(String password) {
    try {
      // Create MessageDigest instance for SHA-256
      final var md = MessageDigest.getInstance("SHA-256");
      // Add password bytes to digest
      md.update(password.getBytes());
      // Get the hash's bytes
      final var bytes = md.digest();
      // Convert byte[] to hexadecimal format
      final var sb = new StringBuilder();
      for (final byte b : bytes) {
        sb.append(String.format("%02x", b));
      }
      return sb.toString();
    } catch (final NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Optional<Username> parseUsername(String username) {
    Objects.requireNonNull(username);
    if (isValidUsername(username))
      return null; // Optional.of(new UsernameImpl(username));

    return Optional.empty();
  }

  @Override
  public Optional<Password> parsePassword(String password) {
    Objects.requireNonNull(password);
    if (isValidPassword(password))
      return null; // Optional.of(new PasswordImpl(hashPassword(password)));

    return Optional.empty();
  }

}
