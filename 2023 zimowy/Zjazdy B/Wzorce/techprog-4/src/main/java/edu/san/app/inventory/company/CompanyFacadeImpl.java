// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.app.inventory.company;

import edu.san.logic.inventory.company.CompanyFacade;
import edu.san.logic.inventory.company.Email;
import edu.san.logic.inventory.employee.EmployeeId;
import edu.san.logic.inventory.employee.FirstName;
import edu.san.logic.inventory.employee.LastName;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
class CompanyFacadeImpl implements CompanyFacade {

  private final EmailEntityRepositoryImpl emailRepository;

  CompanyFacadeImpl(EmailEntityRepositoryImpl emailRepository) {
    this.emailRepository = emailRepository;
  }

  @Override
  @Transactional
  public Email createEmail(EmployeeId employeeId, FirstName firstName,
      LastName lastName) {    
    
    final var emailValue = employeeId + "_" + firstName + "_" + lastName + "@inventory.com";
    EmailEntity emailEntity = new EmailEntity();
    emailEntity.value = emailValue;
    emailRepository.persist(emailEntity);
    return emailEntity;
  }

}
