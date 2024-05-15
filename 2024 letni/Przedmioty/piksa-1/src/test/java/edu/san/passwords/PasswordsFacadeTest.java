// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.passwords;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.passwords.app.NonBlankPasswordQuery;
import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class PasswordsFacadeTest {

  @Inject
  PasswordsFacade passwordsFacade;

  @BeforeEach
  void setUp() throws Exception {
    assertThat(passwordsFacade).isNotNull();
    Log.info(passwordsFacade.getClass());
  }

  @Test
  void testIsStrong() {
    final var result = passwordsFacade
        .isStrong(new NonBlankPasswordQuery("abcdefgh"));
    assertThat(result).isFalse();
  }

}
