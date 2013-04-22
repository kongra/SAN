package san.jdbc.open;

import san.jdbc.IntRef;
import san.jdbc.JDBCWrites;
import san.jdbc.WithJDBCConnection;
import san.jdbc.WithJDBCQueryResults;
import san.jdbc.WithinJDBCTransacion;

public class OType {

  public static OType intern(final String name) {
    final IntRef id = new IntRef(-1);

    new WithJDBCConnection() {
      @Override
      protected void run() {
        new WithinJDBCTransacion(connection) {
          @Override
          protected void run() {
            // T
            new WithJDBCQueryResults(connection,
                "select id from otypes where name='" + name + "'") {
              @Override
              public void step() {
                id.value = get("id");
              }
            };

            if (id.value == -1) {
              JDBCWrites.update(connection,
                "insert into otypes(name) values ('" + name + "')");

              new WithJDBCQueryResults(connection,
                  "select id from otypes where name='" + name + "'") {
                @Override
                public void step() {
                  id.value = get("id");
                }
              };
            }

            // ~T
          }
        };
      }
    };

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
