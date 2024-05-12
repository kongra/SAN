// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.dbi;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RSExpr<T> {
  RSStepResult<T> eval(ResultSet rs) throws SQLException;

  sealed interface RSStepResult<T> {
    record Break<T>() implements RSStepResult<T> {}

    record Continue<T>() implements RSStepResult<T> {}

    record Result<T>(T value) implements RSStepResult<T> {}
  }
}
