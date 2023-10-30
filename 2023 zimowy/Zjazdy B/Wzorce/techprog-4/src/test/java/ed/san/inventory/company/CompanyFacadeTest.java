package ed.san.inventory.company;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CompanyFacadeTest {

  CompanyFacade companyFacade;

  @Test
  void testCreateEmail() {
    // final var email = companyFacade.createEmail();
    assertThat(companyFacade).isNotNull();
  }
}
