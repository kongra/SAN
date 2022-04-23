package edu.san.rest;

import java.lang.System.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/hello")
public class SampleResource {

  @Inject
  private HelloWorker helloWorker;

  public SampleResource() {
    LOG.log(Logger.Level.WARNING, "SampleResource::constructor()");
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    LOG.log(Logger.Level.WARNING, "SampleResource::hello()");
    return helloWorker.sayHello();
  }

  private static final Logger LOG = System
      .getLogger(SampleResource.class.getName());

}
