// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import edu.san.passwords.PasswordStrengthSuggestion;
import edu.san.passwords.PasswordsFacade;
import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.ConfigurationBuilder;
import telsos.string.NonBlank;

class PasswordsFacadeImpl implements PasswordsFacade {

  private final Nbvcxz passwordStrengthEstimator;

  PasswordsFacadeImpl() {
    final var configration = new ConfigurationBuilder()
        .setMinimumEntropy(20.0)
        .createConfiguration();
    passwordStrengthEstimator = new Nbvcxz(configration);
  }

  NbvcxzScore passwordScore(NonBlank password) {
    final var result = passwordStrengthEstimator
        .estimate(password.value());
    return new NbvcxzScore(result.getEntropy(), result.isMinimumEntropyMet());
  }

  @Override
  public boolean isStrong(NonBlank password) {
    return passwordScore(password).isMinimumEntropyMet();
  }

  @Override
  public PasswordStrengthSuggestion getPasswordStrengthSuggestion(
      NonBlank password) {
    // TODO Auto-generated method stub
    return null;
  }

}
