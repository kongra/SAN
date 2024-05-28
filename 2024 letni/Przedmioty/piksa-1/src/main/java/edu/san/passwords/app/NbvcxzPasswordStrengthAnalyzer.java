// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import java.util.Objects;
import java.util.Optional;

import edu.san.passwords.PasswordsStrengthAnalyzer;
import jakarta.enterprise.context.ApplicationScoped;
import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.ConfigurationBuilder;
import telsos.strings.NonBlank;

@ApplicationScoped
class NbvcxzPasswordStrengthAnalyzer
    extends PasswordsStrengthAnalyzer
    implements PasswordsStrengthAnalyzer.ImprovementOutputFactory {

  private final Nbvcxz nbvcxz;

  NbvcxzPasswordStrengthAnalyzer() {
    final var configration = new ConfigurationBuilder()
        .setMinimumEntropy(20.0)
        .createConfiguration();
    nbvcxz = new Nbvcxz(configration);
  }

  @Override
  public AnalysisOutput analyze(NonBlank password) {
    final var estimate = nbvcxz.estimate(password.value());

    record OutputImpl(Number strength, boolean isStrong)
        implements AnalysisOutput {}

    return new OutputImpl(
        estimate.getEntropy(),
        estimate.isMinimumEntropyMet());
  }

  @Override
  protected ImprovementOutputFactory getImprovementOutputFactory() {
    return this;
  }

  @Override
  public ImprovementOutput create(Number strength, boolean isStrong,
      NonBlank passwordMask) {

    Objects.requireNonNull(strength);

    record ImprovementOutputImpl(
        Number strength,
        boolean isStrong,
        Optional<NonBlank> strongerPasswordMask) implements ImprovementOutput {}

    return new ImprovementOutputImpl(
        strength,
        isStrong,
        Optional.of(passwordMask));
  }

  @Override
  public ImprovementOutput create(Number strength, boolean isStrong) {

    Objects.requireNonNull(strength);

    record NoImprovementOutputImpl(
        Number strength,
        boolean isStrong) implements ImprovementOutput {

      @Override
      public Optional<NonBlank> strongerPasswordMask() {
        return Optional.empty();
      }
    }

    return new NoImprovementOutputImpl(strength, isStrong);
  }

}
