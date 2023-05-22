package edu.san;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Greeter {

  @SuppressWarnings("static-method")
  public String greetMe(String name) {
    return "Hello " + name + "!!!";
  }

}
