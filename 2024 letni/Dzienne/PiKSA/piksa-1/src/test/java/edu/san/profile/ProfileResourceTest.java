// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.profile;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfileResourceTest {

  ProfileResource profileResource;

  @BeforeEach 
  void beforeEach() {
    profileResource = new ProfileResource();
  }

  @Test
  void testSignIn() {
    assertThat(profileResource).isNotNull();
  }

}
