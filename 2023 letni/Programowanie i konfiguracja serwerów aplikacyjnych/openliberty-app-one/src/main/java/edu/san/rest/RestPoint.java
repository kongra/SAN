// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.rest;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@SuppressWarnings("static-method")
public class RestPoint {

  @Path("hello")
  @Produces(MediaType.TEXT_PLAIN)
  public Response hello() {
    return Response.ok("hello").build();
  }

}
