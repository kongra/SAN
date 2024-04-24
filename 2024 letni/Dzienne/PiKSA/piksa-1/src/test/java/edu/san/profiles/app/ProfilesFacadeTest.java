// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.profiles.ProfilesFacade;
import edu.san.profiles.ProfilesFactory;
import edu.san.profiles.ProfilesRepository;

class ProfilesFacadeTest {

  ProfilesFacade profilesFacade;

  @BeforeEach
  void setUp() throws Exception {
    final ProfilesRepository profilesRepository = new MutableProfilesRepositoryImpl();
    final ProfilesFactory profilesFactory = new ProfilesFactoryImpl();
    profilesFacade = new ProfilesFacade(profilesRepository, profilesFactory);
  }

  @Test
  void testCreateUsername() {
    // fail("Not yet implemented");
  }

  @Test
  void testCreatePassword() {
    final var password = profilesFacade
        .createPassword("^%$^%$DSS&D%^S^%D$KJBN876253576KJBD");
    assertThat(password).isNotEmpty();
  }

}
