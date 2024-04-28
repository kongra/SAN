// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import edu.san.passwords.PasswordsStrengthAnalyzer;
import jakarta.enterprise.context.ApplicationScoped;
import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.ConfigurationBuilder;
import telsos.string.NonBlank;

@ApplicationScoped
class NbvcxzPasswordStrengthAnalyzer
    implements PasswordsStrengthAnalyzer {

  private final Nbvcxz nbvcxz;

  NbvcxzPasswordStrengthAnalyzer() {
    final var configration = new ConfigurationBuilder()
        .setMinimumEntropy(20.0)
        .createConfiguration();
    nbvcxz = new Nbvcxz(configration);
  }

  @Override
  public boolean isMinimumEntropyMet(NonBlank password) {
    return nbvcxz.estimate(password.value()).isMinimumEntropyMet();
  }

}
