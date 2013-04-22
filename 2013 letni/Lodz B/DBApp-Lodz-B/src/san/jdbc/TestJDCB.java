package san.jdbc;

import java.sql.SQLException;
import san.jdbc.open.OType;

public class TestJDCB {

  public static void main(String... args) throws ClassNotFoundException,
      SQLException {
    Class.forName("org.postgresql.Driver");

    OType Url = OType.intern("URL");
    System.out.println(Url);

    // new WithJDBCConnection(DriverManager.getConnection(
    // "jdbc:postgresql://localhost/MAAS", "scott", "tiger")) {
    // @Override
    // protected void run() {
    // new WithinJDBCTransacion(connection) {
    // @Override
    // protected void run() {
    // new WithJDBCQueryResults(connection, "select * from users") {
    // @Override
    // public void step() {
    // String login = get("login");
    // Long id = get("id");
    //
    // System.out.println(login + " " + id);
    //
    // JDBCWrites.update(connection,
    // "insert into users(login, passwd) values ('" + login + id
    // + "', md5('1234'))");
    // }
    // };
    // }
    // };
    // }
    // };

  }

}
