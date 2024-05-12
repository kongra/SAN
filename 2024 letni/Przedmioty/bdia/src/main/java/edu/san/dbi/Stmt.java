// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.dbi;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface Stmt extends Expr<Void> {

  void exec(Connection conn) throws SQLException;

  @Override
  default Void eval(Connection conn) throws SQLException {
    exec(conn);
    return null;
  }

}
