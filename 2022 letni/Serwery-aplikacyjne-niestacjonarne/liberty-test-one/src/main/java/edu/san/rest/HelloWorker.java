package edu.san.rest;

import java.lang.System.Logger;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloWorker {

  public HelloWorker() {
    LOG.log(Logger.Level.WARNING, "HelloWorker::constructor()");
  }

  @SuppressWarnings("static-method")
  public String sayHello() {
    return "Hello :) " + System.currentTimeMillis();
  }

  private static final Logger LOG = System
      .getLogger(HelloWorker.class.getName());
}
