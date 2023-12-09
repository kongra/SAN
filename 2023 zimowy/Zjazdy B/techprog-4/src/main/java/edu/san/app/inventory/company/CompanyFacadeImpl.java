// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.app.inventory.company;

import java.util.Objects;

import edu.san.logic.inventory.company.CompanyFacade;
import edu.san.logic.inventory.company.Email;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
class CompanyFacadeImpl implements CompanyFacade {

  private final EmailEntityRepository emailEntityRepository;

  CompanyFacadeImpl(EmailEntityRepository emailEntityRepository) {
    this.emailEntityRepository = Objects
        .requireNonNull(emailEntityRepository);
  }

  @Override
  @Transactional
  public Email persistEmail(String emailValue) {
    final var emailEntity = new EmailEntity(emailValue);
    emailEntityRepository.persist(emailEntity);    
    return emailEntity;
  }

}
