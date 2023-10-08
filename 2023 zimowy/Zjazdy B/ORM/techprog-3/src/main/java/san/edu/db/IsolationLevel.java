// © 2023 Konrad Grzanek <kongra@gmail.com>
package san.edu.db;

import java.sql.Connection;

public enum IsolationLevel {

  NONE(Connection.TRANSACTION_NONE),
  READ_UNCOMMITTED(Connection.TRANSACTION_READ_COMMITTED),
  READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
  REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
  SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

  public final int value;

  IsolationLevel(int value) {
    this.value = value;
  }

}
