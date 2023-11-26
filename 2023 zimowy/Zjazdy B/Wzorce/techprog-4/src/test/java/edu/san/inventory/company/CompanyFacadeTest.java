package edu.san.inventory.company;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import edu.san.logic.inventory.company.CompanyFacade;

class CompanyFacadeTest {

  CompanyFacade companyFacade;

  @Test
  void testCreateEmail() {
    // final var email = companyFacade.createEmail();
    assertThat(companyFacade).isNotNull();
  }
}
