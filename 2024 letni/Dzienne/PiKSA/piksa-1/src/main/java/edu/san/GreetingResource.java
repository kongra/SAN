package edu.san;

import java.util.concurrent.atomic.AtomicLong;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

  private final AtomicLong counter = new AtomicLong(0);

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    final var value = counter.incrementAndGet();
    return "Hello from RESTEasy Reactive %d!!!".formatted(value);
  }
}
