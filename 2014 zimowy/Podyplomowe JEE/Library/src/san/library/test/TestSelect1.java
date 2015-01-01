package san.library.test;

import san.jdbc.JDBC;
import san.util.Body;
import san.util.Doclean;

public class TestSelect1 {

  public static void main(String[] args) {
    Doclean.run(new Runnable() {
      @Override
      public void run() {
        JDBC.withConnection(JDBC.pool(), new Runnable() {
          @Override
          public void run() {
            processQuery1();
          }
        });
      }
    });
  }

  private static void processQuery1() {
    JDBC.withQueryResults("select * from books", new Body() {
      @Override
      public void run() throws Break, Continue {
        Long id = JDBC.get("id");
        String author = JDBC.get("author");
        String title = JDBC.get("title");
        System.out.println(id + " " + author + " " + title);
      }
    });
  }
}
