package edu.san.authentication.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method")
class ProfileEntityTest {

  @Test
  void testProfileEntitiesDetachedCreation() {
    final var profileEntity1 = new ProfileEntity();

    final var profileEntity2 = new ProfileEntity();

    final var profileEntity3 = new ProfileEntity();

    assertThat(profileEntity1)
        .isNotEqualTo(profileEntity2)
        .isNotEqualTo(profileEntity3);

    assertThat(profileEntity2)
        .isNotEqualTo(profileEntity3);
  }

}
