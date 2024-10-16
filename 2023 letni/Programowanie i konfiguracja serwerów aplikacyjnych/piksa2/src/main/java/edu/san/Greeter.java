// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Greeter {

  @SuppressWarnings("static-method")
  public String greeting() {
    return "Hello World!!!";
  }

}
