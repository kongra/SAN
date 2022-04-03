package edu.san.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@SuppressWarnings("static-method")
@Path("/hello")
public class SampleResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Test content produced, stamp: " + System.currentTimeMillis();
  }

}
