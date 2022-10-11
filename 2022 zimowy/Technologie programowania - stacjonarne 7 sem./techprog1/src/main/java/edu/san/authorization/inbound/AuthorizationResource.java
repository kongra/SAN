package edu.san.authorization.inbound;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authorization")
class AuthorizationResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  Response createProfile(ProfileDTO profileDTO) {
    // TODO: Use JSON-P for Response
    
    return null;
  }
  
}
