// © 2023 Konrad Grzanek <kongra@gmail.com>
package san.edu;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TestComponent {

  public String greet() {
    runInTx();
    return "Hello World!";
  }

  @SuppressWarnings("static-method")
  @Transactional
  private void runInTx() {
    System.out.println("I'm in a transaction!");
  }

}
