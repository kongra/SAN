package san.jee1.profile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProfileTools {

  private static final Map<String, String> credentials = new HashMap<>();

  static {
    register("kongra@gmail.com", "12345");
    register("kgrzanek@san.edu.pl", "54321");
  }

  public static synchronized void register(String email, String password) {
    Objects.requireNonNull(email);
    Objects.requireNonNull(password);
    credentials.put(email, password);
  }

  public static synchronized boolean isRegistered(String email, String password) {
    String p1 = credentials.get(email);
    if (p1 == null)
      return false; // email unknown

    return p1.equals(password);
  }

  private ProfileTools() {

  }

}
