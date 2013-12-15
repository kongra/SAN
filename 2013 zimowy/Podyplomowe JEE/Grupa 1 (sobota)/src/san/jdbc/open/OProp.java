package san.jdbc.open;

import san.jdbc.JDBC;
import san.util.Body;
import san.util.Doclean;
import san.util.refs.IntRef;

public class OProp {

  public static OProp ofName(final String name) {
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
                    "select id from oprops where name='" + name + "'";
                Body body = new Body() {
                  @Override
                  public void run() throws Break, Continue {
                    id.value = JDBC.get("id");
                  }
                };

                int steps = JDBC.withQueryResults(query, body);
                if (steps == 0) {
                  // NO MATCHING RECORDS, LET'S CREATE ONE ...
                  JDBC.executeUpdate("insert into oprops(name) values ('"
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

    return new OProp(id.value, name);
  }

  private final int id;

  private final String name;

  private OProp(int id, String name) {
    this.id = id;
    this.name = name;
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
    OProp other = (OProp) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  public int id() {
    return id;
  }

  public String name() {
    return name;
  }
  
}
