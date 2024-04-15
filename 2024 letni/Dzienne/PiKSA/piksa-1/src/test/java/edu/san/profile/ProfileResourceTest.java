// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profile;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfileResourceTest {

  ProfileResource profileResource;

  ProfileManager profileManager;

  ProfileRepository profileRepository;

  @BeforeEach
  void beforeEach() {
    profileRepository = new ProfileRepositoryFake();
    profileManager = new ProfileManager(profileRepository);
    profileResource = new ProfileResource(profileManager);
  }

  @Test
  void testSignIn() {
    assertThat(profileResource).isNotNull();
  }

}
