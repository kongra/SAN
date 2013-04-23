package san.jdbc;

import san.util.Body;
import san.util.Doclean;

public class TestJDCB {

  public static void main(String... args) {
    Doclean.run(new Runnable() {
      @Override
      public void run() {
        JDBC.withConnection(JDBC.pool(), new Runnable() {
          @Override
          public void run() {
            JDBC.withQueryResults("select * from users", new Body() {
              @Override
              public void run() throws Break, Continue {
                System.out.println(JDBC.get("login"));
              }
            });
          }
        });
      }
    });
  }

}
