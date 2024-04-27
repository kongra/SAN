// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.profiles.ProfilesFacade;
import edu.san.profiles.ProfilesRepository;

class ProfilesFacadeTest {

  ProfilesFacade profilesFacade;

  @BeforeEach
  void setUp() throws Exception {
    final ProfilesRepository profilesRepository = new MutableProfilesRepositoryImpl();
    profilesFacade = new ProfilesFacade(profilesRepository);
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
