package edu.san;

import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello from Quarkus REST " + persistNewMyEntity();
  }

  @Transactional
  public String persistNewMyEntity() {
    var entity1 = new MyEntity();
    entity1.field = "field-1";
    entity1.persist();

    var entities = MyEntity.listAll();
    Log.warn("Entities are " + entities);

    return entities.toString();
  }
}
