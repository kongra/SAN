package ewus.emp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ewus.Currency;
import ewus.EmpID;
import ewus.Employee;
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
    panKowalski.setDeptId(1);
    panKowalski.setId(1);
    panKowalski.setFirstName(firstName);
    panKowalski.setLastName(lastName);
    panKowalski.setSalary(salary);

    Employee paniKowalska = new Employee();
    paniKowalska.setDeptId(1);
    paniKowalska.setId(2);
    paniKowalska.setFirstName("Anna");
    paniKowalska.setLastName("Kowalska");
    paniKowalska.setSalary(salary);

    // paniKowalska.setManager(panKowalski);
    panKowalski.setManagedEmployees(Arrays.asList(paniKowalska));

    EntityManager em = null;

    try {
      em = emFactory.createEntityManager();
      em.getTransaction().begin();

      em.persist(panKowalski);
      em.persist(paniKowalska);

      em.flush();

      Employee anna = em.find(Employee.class, new EmpID(1, 2));
      System.out.println("Anna zarabia " + anna.getSalary());

      em.getTransaction().commit();
    }
    finally {
      if (em != null) {
        em.close();
      }
    }

  }

}
