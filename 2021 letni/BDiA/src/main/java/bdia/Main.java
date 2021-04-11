package bdia;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

  public static void main(String... args) throws SQLException {
    DBI.withConn(() -> {
      DBI.restartingTx(() -> {
        DBI.withNewTransaction(Connection.TRANSACTION_SERIALIZABLE, () -> {
          int n = DBI.execUpdate("Update profiles set amount = amount - 100 " +
              "where email = 'kongra@gmail.com'");
          System.out.println("Modified " + n + " records");

          // TODO:
          // update("profiles").set("amount", to("amount-1")).where("email", is("kongra@gmail.com"));

        });
      });
    });
  }

}
