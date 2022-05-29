package edu.san;

import java.lang.System.Logger;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class HelloWorker {

  public HelloWorker() {
    LOG.log(Logger.Level.INFO, "HelloWorker::constructor()");
  }

  @SuppressWarnings("static-method")
  public String sayHello() {
    return "Hello :) " + System.currentTimeMillis();
  }

  private static final Logger LOG = System
      .getLogger(HelloWorker.class.getName());

}
