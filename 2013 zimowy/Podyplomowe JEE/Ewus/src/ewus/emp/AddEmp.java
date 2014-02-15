package ewus.emp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ewus.Address;
import ewus.Currency;
import ewus.Doctor;
import ewus.Employee;
import ewus.Manager;
import ewus.Money;
import ewus.Phone;

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

    Address addr = new Address();
    addr.setStreet("Piotrkowska");
    addr.setNumber("123B");
    addr.setCode("93-432");
    
    Phone ph1 = new Phone();
    ph1.setType("private");
    ph1.setOwner(paniKowalska);
    
    Phone ph2 = new Phone();
    ph2.setType("fax");
    ph2.setOwner(paniKowalska);
    
    paniKowalska.setAddress(addr);
    Map<String, Phone> phones = new HashMap<>();
    phones.put("private", ph1);
    phones.put("fax", ph2);
    paniKowalska.setPhones(phones);
    
    EntityManager em = null;

    try {
      em = emFactory.createEntityManager();
      em.getTransaction().begin();

      // em.persist(addr);
      em.persist(paniKowalska);
      em.persist(panKowalski);
      em.persist(manager);
      em.persist(doc);

      em.flush();

      List<Employee> emps = manager.getManagedEmployees();
      System.out.println(emps.getClass());
      
      for (Employee emp : emps) {       
        System.out.println(emp.getId() + " " + emp.getFirstName() + " "
            + emp.getLastName());
      }

      em.getTransaction().commit();
    }
    finally {
      if (em != null) {
        em.close();
      }
    }

  }

}
