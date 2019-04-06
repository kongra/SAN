package san.jee6.jdbc;

import java.sql.SQLException;

public interface Unary<T, S> {

  S call(T arg) throws SQLException;

}
