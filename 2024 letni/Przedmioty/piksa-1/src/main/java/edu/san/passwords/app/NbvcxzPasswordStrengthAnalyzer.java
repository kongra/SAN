// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import java.util.Objects;

import edu.san.passwords.PasswordsStrengthAnalysisOutput;
import edu.san.passwords.PasswordsStrengthAnalyzer;
import edu.san.passwords.PasswordsStrengthImprovementOutput;
import edu.san.passwords.PasswordsStrengthImprovementOutputFactory;
import jakarta.enterprise.context.ApplicationScoped;
import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.ConfigurationBuilder;
import telsos.strings.NonBlank;

@ApplicationScoped
class NbvcxzPasswordStrengthAnalyzer
    extends PasswordsStrengthAnalyzer
    implements PasswordsStrengthImprovementOutputFactory {

  private final Nbvcxz nbvcxz;

  NbvcxzPasswordStrengthAnalyzer() {
    final var configration = new ConfigurationBuilder()
        .setMinimumEntropy(20.0)
        .createConfiguration();
    nbvcxz = new Nbvcxz(configration);
  }

  @Override
  public PasswordsStrengthAnalysisOutput analyze(NonBlank password) {
    final var estimate = nbvcxz.estimate(password.value());
    return new PasswordsStrengthImprovementOutputImpl(
        estimate.getEntropy(),
        estimate.isMinimumEntropyMet(),
        null);
  }

  @Override
  protected PasswordsStrengthImprovementOutputFactory improvementOutputFactory() {
    return this;
  }

  @Override
  public PasswordsStrengthImprovementOutput create(Number strength,
      boolean isStrong,
      NonBlank passwordMask) {
    return new PasswordsStrengthImprovementOutputImpl(
        strength, isStrong, Objects.requireNonNull(passwordMask));
  }

  @Override
  public PasswordsStrengthImprovementOutput create(Number strength,
      boolean isStrong) {
    return new PasswordsStrengthImprovementOutputImpl(strength, isStrong, null);
  }

}
