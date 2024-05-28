// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import telsos.strings.NonBlank;

@QuarkusTest
class PasswordsStrengthAnalyzerTest {

  @Inject
  PasswordsStrengthAnalyzer passwordsStrengthAnalyzer;

  @BeforeEach
  void setUp() {
    assertThat(passwordsStrengthAnalyzer).isNotNull();
  }

  @Test
  void testAnalyze() {
    final var password = NonBlank.of("abcdefgh").orElseThrow();
    final var output = passwordsStrengthAnalyzer.analyze(password);
    assertThat(output.isStrong()).isFalse();
    assertThat(output.strength().doubleValue()).isLessThan(20.0);
  }

  @Test
  void testSuggestImprovementIfNeeded() {
    final var password = NonBlank.of("abcdefgh").orElseThrow();
    final var output = passwordsStrengthAnalyzer
        .suggestImprovementIfNeeded(password);

    assertThat(output).isNull();
  }

}
