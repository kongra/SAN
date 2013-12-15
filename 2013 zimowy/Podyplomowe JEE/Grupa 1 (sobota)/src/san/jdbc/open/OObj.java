package san.jdbc.open;

import san.jdbc.JDBC;
import san.util.Body;
import san.util.refs.Ref;

public class OObj {

  private final long id;

  private final OType otype;

  public OObj(long id, OType otype) {
    this.id = id;
    this.otype = otype;
  }

  public void set(final OProp prop, final Object value) {
    JDBC.inDefaultENV(new Runnable() {
      @Override
      public void run() {
        String valPhrase =
            value == null ? "NULL" : "'" + value.toString() + "'";

        int count =
            JDBC.executeUpdate("update ovalues set val=" + valPhrase
                + " where oobject=" + id() + " and oprop=" + prop.id());
        if (count == 0) {
          JDBC.executeUpdate("insert into ovalues(oobject, oprop, val) values("
              + id() + ", " + prop.id() + "," + valPhrase + ")");
        }
      }
    });
  }

  public void set(String prop, Object value) {
    set(OProp.ofName(prop), value);
  }

  public String get(final OProp prop) {
    final Ref<String> val = Ref.initially(null);

    JDBC.inDefaultENV(new Runnable() {
      @Override
      public void run() {
        JDBC.withQueryResults("select val from ovalues where oobject=" + id()
            + " and oprop=" + prop.id(), new Body() {
          @Override
          public void run() throws Break, Continue {
            val.value = JDBC.get("val");
          }
        });
      }
    });
    
    return val.value;
  }

  public String get(String prop) {
    return get(OProp.ofName(prop));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
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
    OObj other = (OObj) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  public long id() {
    return id;
  }

  public OType otype() {
    return otype;
  }

}
