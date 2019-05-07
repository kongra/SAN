import static spark.Spark.*;

public class Program {

  public static void main(String... args) {
    get("/hello", (req, res) -> {
      String login = req.queryParams("login");

      return "Hello " + login + "!!!";
    });
  }

}
