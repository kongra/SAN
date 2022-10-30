package edu.san.authentication.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method")
class ProfileEntityTest {

  @Test
  void testProfileEntitiesDetachedCreation() {
    var profileEntity1 = new ProfileEntity();
    
    var profileEntity2 = new ProfileEntity();
    
    var profileEntity3 = new ProfileEntity();
    
    assertThat(profileEntity1)
      .isNotEqualTo(profileEntity2)
      .isNotEqualTo(profileEntity3);    
    
    assertThat(profileEntity2)
      .isNotEqualTo(profileEntity3);
  }

}
