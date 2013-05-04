package test.san.jdbc;

import san.jdbc.JDBC;
import san.util.Body;
import san.util.Doclean;

public class TestJDCB {

  public static void main(String... args) {
    // iterUsers();
    
    prepareAndIterUsers();
  }

  private static void prepareAndIterUsers() {
    JDBC.withConnection(JDBC.pool(), new Runnable() {
      @Override
      public void run() {
        iterUsers();
      }
    });
  }

  private static void iterUsers() {
    JDBC.withQueryResults("select * from users", new Body() {
      @Override
      public void run() throws Break, Continue {
        System.out.println(JDBC.get("login"));
      }
    });
  }

}
