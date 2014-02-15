package ewus.emp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ewus.Currency;
import ewus.Doctor;
import ewus.Employee;
import ewus.Manager;
import ewus.Money;

@WebServlet("/emp/AddEmp")
public class AddEmp extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @PersistenceUnit(unitName = "Ewus")
  private EntityManagerFactory emFactory;

  public AddEmp() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String firstName = "Jan";
    String lastName = "Kowalski";
    Money salary = new Money(new BigDecimal("2500.04"), Currency.GBP);

    Employee panKowalski = new Employee();
    panKowalski.setFirstName(firstName);
    panKowalski.setLastName(lastName);
    panKowalski.setSalary(salary);

    Employee paniKowalska = new Employee();
    paniKowalska.setFirstName("Anna");
    paniKowalska.setLastName("Kowalska");
    paniKowalska.setSalary(salary);

    Manager manager = new Manager();
    manager.setFirstName("Adam");
    manager.setLastName("Nowak");
    manager.setManagedEmployees(Arrays.asList(panKowalski, paniKowalska));
    
    Doctor doc = new Doctor();
    doc.setFirstName("Michał");
    doc.setLastName("Nowakowski");
    doc.setSpec("Ginekologia");
    
    // paniKowalska.setManager(panKowalski);
    // panKowalski.setManagedEmployees(Arrays.asList(paniKowalska));

    EntityManager em = null;

    try {
      em = emFactory.createEntityManager();
      em.getTransaction().begin();

      em.persist(panKowalski);
      em.persist(paniKowalska);
      em.persist(manager);
      em.persist(doc);

      em.flush();

//      Employee anna = em.find(Employee.class, paniKowalska.getId());
//      System.out.println("Anna zarabia " + anna.getSalary());      
//      Doctor lekarz = em.find(Doctor.class, doc.getId());

      em.getTransaction().commit();
    }
    finally {
      if (em != null) {
        em.close();
      }
    }

  }

}
