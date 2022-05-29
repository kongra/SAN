package edu.san;

import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@ApplicationScoped
public class GreetingResource {

  private final HelloTools helloTools;

  @Inject
  public GreetingResource(HelloTools helloTools) {
    Objects.requireNonNull(helloTools);
    this.helloTools = helloTools;
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Test RESTEasy " + System.currentTimeMillis() + " "
        + helloTools.getResult() + " with " + helloTools.getClass();
  }

  {
    System.out.println("GreetingResource::constructor()");
  }
}
