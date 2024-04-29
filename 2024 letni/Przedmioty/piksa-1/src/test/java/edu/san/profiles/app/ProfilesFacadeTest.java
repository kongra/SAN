// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.profiles.ProfilesFacade;
import edu.san.profiles.ProfilesParser;
import edu.san.profiles.ProfilesRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
class ProfilesFacadeTest {

  @Inject
  ProfilesRepository profilesRepository;

  @Inject
  ProfilesParser profilesParser;

  ProfilesFacade profilesFacade;

  @BeforeEach
  void setUp() throws Exception {
    assertThat(profilesRepository).isNotNull();
    assertThat(profilesParser).isNotNull();

    profilesFacade = new ProfilesFacade(profilesRepository);
    assertThat(profilesFacade).isNotNull();
  }

  @Test
  void testCreateUsername() {
    // fail("Not yet implemented");
  }

  @Test
  void testCreatePassword() {
//    final var password = profilesFacade
//        .createPassword("^%$^%$DSS&D%^S^%D$KJBN876253576KJBD");
//    assertThat(password).isNotEmpty();
  }

}
