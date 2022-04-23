package edu.san.rest;

import java.lang.System.Logger;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/hello")
public class HelloResource {

  private final HelloUtils helloUtils;

  private final HelloCounter helloCounter;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String sayHello() {
    LOG.log(Logger.Level.DEBUG, "HelloResource::sayHello()");
    return helloUtils.genHello("Test-1");
  }

  @Path("/update")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateCounter() {
    LOG.log(Logger.Level.DEBUG, "HelloResource::updateCounter()");
    var n = helloCounter.update();
    var json = Map.of("n", n);

    return Response.ok(json).build();
  }

  @Inject
  public HelloResource(HelloUtils helloUtils, HelloCounter helloCounter) {
    LOG.log(Logger.Level.DEBUG,
        "HelloResource::constructor " + helloUtils + "," + helloCounter);
    this.helloUtils = helloUtils;
    this.helloCounter = helloCounter;
  }

  public HelloResource() {
    this(null, null);
  }

  private static final Logger LOG = System
      .getLogger(HelloResource.class.getName());

}
