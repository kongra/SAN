// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles;

import java.util.Optional;

public interface ProfilesParser {

  // validate: a -> a|null, Optional<a>
  // parse:    a ->         Optional<b>

  // data Either a b = Left a | Right b

  Optional<Username> parseUsername(String username);

  Optional<Password> parsePassword(String password);

}
