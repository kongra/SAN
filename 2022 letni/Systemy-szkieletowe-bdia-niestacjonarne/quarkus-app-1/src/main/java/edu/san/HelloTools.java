package edu.san;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class HelloTools {

  public String getResult() {
    System.out.println("HelloTools::getResult()");
    return "Hello!";
  }

  {
    System.out.println("HelloTools::constructor()");
  }

}
