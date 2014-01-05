package san.jee.lect06;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import san.jdbc.JDBC;
import san.util.Doclean;
import san.util.LookaheadIterator;

public abstract class Employee {

  public static Iterable<Employee> all() {
    return new Iterable<Employee>() {
      @SuppressWarnings("synthetic-access")
      @Override
      public Iterator<Employee> iterator() {
        return all("select * from emps");
      }
    };
  }

  private static Iterator<Employee> all(String query) {
    final Iterator<ResultSet> it = JDBC.resultSetIterator(query);
    return new Iterator<Employee>() {

      @Override
      public boolean hasNext() {
        return it.hasNext();
      }

      @Override
      public Employee next() {
        final ResultSet rs = it.next();

        return new Employee() {
          @Override
          public Long id() {
            try {
              return rs.getLong("id");
            }
            catch (SQLException e) {
              throw new RuntimeException(e);
            }
          }

          @Override
          public String name() {
            try {
              return rs.getString("name");
            }
            catch (SQLException e) {
              throw new RuntimeException(e);
            }
          }

          @Override
          public String surname() {
            try {
              return rs.getString("surname");
            }
            catch (SQLException e) {
              throw new RuntimeException(e);
            }
          }
        };
      }

      @Override
      public void remove() {
        it.remove();
      }
    };
    
  }

  public abstract Long id();

  public abstract String name();

  public abstract String surname();

}
