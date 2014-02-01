package ewus.emp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ewus.Employee;

@WebServlet("/emp/AddEmp")
public class AddEmp extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public AddEmp() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String firstName = "Jan";
    String lastName = "Kowalski";
    BigDecimal salary = new BigDecimal("2500.23");

    Employee panKowalski = new Employee();
    panKowalski.setFirstName(firstName);
    panKowalski.setLastName(lastName);
    panKowalski.setSalary(salary);

    Employee paniKowalska = new Employee();
    paniKowalska.setFirstName("Anna");
    paniKowalska.setLastName("Kowalska");
    paniKowalska.setSalary(salary);

    paniKowalska.setManager(panKowalski);
    panKowalski.setManagedEmployees(Arrays.asList(paniKowalska));

    EntityManagerFactory factory =
        Persistence.createEntityManagerFactory("Ewus");

    EntityManager manager = null;
    try {
      manager = factory.createEntityManager();
      manager.getTransaction().begin();

      manager.persist(panKowalski);
      manager.persist(paniKowalska);

      manager.getTransaction().commit();
    }
    finally {
      if (manager != null) {
        manager.close();
      }
    }

  }

}
