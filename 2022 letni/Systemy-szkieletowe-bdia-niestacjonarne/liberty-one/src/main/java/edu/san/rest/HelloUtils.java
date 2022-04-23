package edu.san.rest;

import java.lang.System.Logger;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HelloUtils {

  public String genHello(String s) {
    return "Hello " + s + " :) " + System.currentTimeMillis();
  }

  public HelloUtils() {
    LOG.log(Logger.Level.DEBUG, "HelloUtils::constructor()");
  }

  private static final Logger LOG = System
      .getLogger(HelloUtils.class.getName());

}
