// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords.app;

import java.util.Objects;
import java.util.Optional;

import edu.san.passwords.PasswordsStrengthAnalyzer;
import jakarta.enterprise.context.ApplicationScoped;
import me.gosimple.nbvcxz.Nbvcxz;
import me.gosimple.nbvcxz.resources.ConfigurationBuilder;
import telsos.math.newtypes.PosLong;
import telsos.strings.NonBlank;

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
  public Output analyze(NonBlank password) {
    final var estimate = nbvcxz.estimate(password.value());

    record OutputImpl(Double strength, boolean isStrong)
        implements PasswordsStrengthAnalyzer.Output {}

    return new OutputImpl(estimate.getEntropy(),
        estimate.isMinimumEntropyMet());
  }

  private record ImprovementOutputImpl(Number strength, boolean isStrong,
      NonBlank passwordMask)
      implements PasswordsStrengthAnalyzer.ImprovementOutput {

    ImprovementOutputImpl {
      Objects.requireNonNull(strength);
    }

    @Override
    public Optional<NonBlank> strongerPasswordMask() {
      return Optional.ofNullable(passwordMask);
    }
  }

  @Override
  public Optional<ImprovementOutput> suggestImprovementIfNeeded(
      NonBlank password, PosLong timeoutMillis) {

    final var output = analyze(password);

    if (output.isStrong())
      return Optional.of(new ImprovementOutputImpl(
          output.strength(),
          output.isStrong(),
          null));

    // TODO Auto-generated method stub
    return Optional.empty();
  }

}
