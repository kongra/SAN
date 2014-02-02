package san.lect06;

import san.jdbc.JDBC;
import san.util.Body;
import san.util.Doclean;

public class UserTools {

  public static Long validUserID(String login, String passwd) {    
    Doclean.run(new Runnable() {
      @Override
      public void run() {
        JDBC.withConnection(JDBC.pool(), new Runnable() {
          @Override
          public void run() {
            JDBC.withQueryResults("select * from users", new Body() {
              @Override
              public void run() {                
                System.out.println(JDBC.get("id") + " " + JDBC.get("login")
                    + " " + JDBC.get("passwd"));
              }
            });
          }
        });
      }
    });

    return null;
  }

  private UserTools() {
    ;
  }

}
