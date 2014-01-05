package san.jee.lect06;

import java.util.Iterator;

import san.jdbc.JDBC;
import san.util.Body;
import san.util.Doclean;

public class TestJDBC {

  static void testSelections() {
    JDBC.withQueryResults("select id, surname from emps", new Body() {

      @Override
      public void run() throws Break, Continue {
        String surname = JDBC.get("surname");
        Long id = JDBC.get("id");

        System.out.println(surname + " " + id);
      }
    });

  }

  static void testInsertions() {
    JDBC.withTransaction(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10_000; i++) {
          String name = "Emp " + i;
          String surname = i + "-Emp";
          String query =
              "insert into emps(name, surname) values ('" + name + "', '"
                  + surname + "')";
          JDBC.executeUpdate(query);
        }
      }
    });
  }

  public static void main(String[] args) {
    // testJDBC();
    Doclean.run(new Runnable() {
      @Override
      public void run() {
        JDBC.withConnection(JDBC.pool(), new Runnable() {
          @Override
          public void run() {
            
            for (Employee employee : Employee.all()) {
              System.out.println(employee.id() + " " + employee.name() + " "
                  + employee.surname());
              break;
            }
          }
        });
      }
    });
  }

  private static void testJDBC() {
    Doclean.run(new Runnable() {
      @Override
      public void run() {
        JDBC.withConnection(JDBC.pool(), new Runnable() {
          @Override
          public void run() {
            testInsertions();
            testSelections();
          }
        });
      }
    });
  }

}
