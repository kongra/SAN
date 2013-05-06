package san.jdbc.open;

import san.jdbc.JDBC;
import san.util.Body;
import san.util.refs.IntRef;
import san.util.refs.LongRef;

public class OType {

  public static OType ofName(final String name) {
    final IntRef id = IntRef.initially(0);

    JDBC.inDefaultENV(new Runnable() {
      @Override
      public void run() {
        String query = "select id from otypes where name='" + name + "'";
        Body body = new Body() {
          @Override
          public void run() throws Break, Continue {
            id.value = JDBC.get("id");
          }
        };

        int steps = JDBC.withQueryResults(query, body);
        if (steps == 0) {
          // NO MATCHING RECORDS, LET'S CREATE ONE ...
          JDBC
              .executeUpdate("insert into otypes(name) values ('" + name + "')");

          // ... AND READ THE NEW ID INTO id.value
          JDBC.withQueryResults(query, body);
        }
      }
    });

    return new OType(id.value, name);
  }

  private final int id;

  private final String name;

  private OType(int id, String name) {
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
    OType other = (OType) obj;
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

  public OObj create() {
    final LongRef oid = LongRef.initially(-1);

    JDBC.inDefaultENV(new Runnable() {
      @Override
      public void run() {
        JDBC.executeUpdate("insert into oobjects(otype) values("
            + OType.this.id() + ")");
        JDBC.withQueryResults(
          "select id from otypes order by id desc limit 1;", new Body() {
            @Override
            public void run() throws Break, Continue {
              Integer value = JDBC.get("id");
              oid.value = value;
            }
          });
      }
    });
    return new OObj(oid.value, this);
  }

}
