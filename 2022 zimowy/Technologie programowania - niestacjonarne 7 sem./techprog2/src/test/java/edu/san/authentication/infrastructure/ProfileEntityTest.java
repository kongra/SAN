package edu.san.authentication.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method")
class ProfileEntityTest {

  @Test
  void testProfileEntitiesDetachedCreation() {
    var profileEntity1 = new ProfileEntity();
    profileEntity1.id = 1L;
    
    var profileEntity2 = new ProfileEntity();
    profileEntity2.id = 1L;
    
    var profileEntity3 = new ProfileEntity();
    profileEntity3.id = 2L;
    
    assertThat(profileEntity1)
      .isEqualTo(profileEntity2)
      .isNotEqualTo(profileEntity3);
    
    assertThat(profileEntity2)
      .isNotEqualTo(profileEntity3);
  }

}
