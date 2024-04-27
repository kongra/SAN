// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import edu.san.passwords.PasswordStrengthAnalysisResult;
import edu.san.passwords.PasswordsFacade;
import jakarta.enterprise.context.ApplicationScoped;
import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.ConfigurationBuilder;
import telsos.string.NonBlank;

@ApplicationScoped
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
  public PasswordStrengthAnalysisResult analyzePasswordStrength(
      NonBlank password) {
    // TODO: implement
    throw new UnsupportedOperationException("Method not yet implemented");
  }

}
