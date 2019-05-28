import static spark.Spark.*;

public class Program {

  public static void main(String... args) {
    get("/profiles", (req, res) -> {
      res.type("application/json");
      return "{\"user\": \"John Doe\"}";
    });
  }

}
