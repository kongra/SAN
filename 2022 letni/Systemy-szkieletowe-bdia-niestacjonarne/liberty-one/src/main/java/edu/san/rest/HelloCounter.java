package edu.san.rest;

import java.io.Serializable;
import java.lang.System.Logger;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.enterprise.context.SessionScoped;

@SessionScoped
public class HelloCounter implements Serializable {

  private static final long serialVersionUID = 1L;

  private final AtomicLong counter = new AtomicLong(0);

  public long update() {
    return counter.incrementAndGet();
  }

  public HelloCounter() {
    LOG.log(Logger.Level.DEBUG, "HelloCounter::constructor()");
  }

  private static final Logger LOG = System
      .getLogger(HelloCounter.class.getName());

}
