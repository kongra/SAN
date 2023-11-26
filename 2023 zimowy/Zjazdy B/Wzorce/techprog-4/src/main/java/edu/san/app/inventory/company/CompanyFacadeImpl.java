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

  private final EmailEntityRepositoryImpl emailEntityRepositoryImpl;

  CompanyFacadeImpl(EmailEntityRepositoryImpl emailRepository) {
    this.emailEntityRepositoryImpl = emailRepository;
  }

  @Override
  @Transactional
  public Email createEmail(EmployeeId employeeId, FirstName firstName,
      LastName lastName) {

    final var emailValue = employeeId.asEmailPrefix() + "_" + firstName + "_"
        + lastName + "@inventory.com";
    final var emailEntity = new EmailEntity();
    emailEntity.value = emailValue;
    emailEntityRepositoryImpl.persist(emailEntity);
    return emailEntity;
  }

}
