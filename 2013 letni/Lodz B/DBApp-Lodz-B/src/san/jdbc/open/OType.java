package san.jdbc.open;

import san.jdbc.JDBC;
import san.util.Body;
import san.util.Doclean;
import san.util.refs.IntRef;

public class OType {

  public static OType ofName(final String name) {
    final IntRef id = IntRef.initially(0);

    Doclean.run(new Runnable() {
      @Override
      public void run() {
        JDBC.withConnection(JDBC.pool(), new Runnable() {
          @Override
          public void run() {
            JDBC.withTransaction(new Runnable() {
              @Override
              public void run() {
                String query =
                    "select id from otypes where name='" + name + "'";
                Body body = new Body() {
                  @Override
                  public void run() throws Break, Continue {
                    id.value = JDBC.get("id");
                  }
                };

                int steps = JDBC.withQueryResults(query, body);
                if (steps == 0) {
                  // NO MATCHING RECORDS, LET'S CREATE ONE ...
                  JDBC.executeUpdate("insert into otypes(name) values ('"
                      + name + "')");

                  // ... AND READ THE NEW ID INTO id.value
                  JDBC.withQueryResults(query, body);
                }
              }
            });
          }
        });
      }
    });

    return new OType(id.value);
  }

  public final int id;

  private OType(int id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    OType other = (OType) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  
}
