// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profiles.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.profiles.ProfileValuesFactory;

class ProfileValuesFactoryImplTest {

  ProfileValuesFactory profileValuesFactory;

  @BeforeEach
  void setUp() throws Exception {
    var profileRepository = new ProfileRepositoryImpl();
    profileValuesFactory = profileRepository.newProfileValuesFactory();
  }

  @Test
  void testCreateUsername() {
    // fail("Not yet implemented");
  }

  @Test
  void testCreatePassword() {
    var password = profileValuesFactory
        .createPassword("^%$^%$DSS&D%^S^%D$KJBN876253576KJBD");
    assertThat(password).isNotEmpty();
  }

}
