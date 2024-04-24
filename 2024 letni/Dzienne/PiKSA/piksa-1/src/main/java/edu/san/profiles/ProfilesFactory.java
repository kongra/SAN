// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles;

import java.util.Optional;

public interface ProfilesFactory {

  Optional<Username> createUsername(String username);

  Optional<Password> createPassword(String password);

}
