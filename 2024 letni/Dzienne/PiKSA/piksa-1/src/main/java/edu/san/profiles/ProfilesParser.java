// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles;

import java.util.Optional;

public interface ProfilesParser {

  Optional<Username> parseUsername(String username);

  Optional<Password> parsePassword(String password);

}
