// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.dbi;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface Expr<T> {
  T eval(Connection conn) throws SQLException;
}