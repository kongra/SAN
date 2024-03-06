package edu.san;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello RESTEasy";
  }
  
  void test1(int[] array) {
    
  }
  
  int signIn(String email, String password) {
    // ...
    
    return 0;
  }
  
  double i(double u, double r) {
    return u / r;
  }
  
  void test() {
    double u1 = 4;
    double r1 = 3;    
    double i1 = i(r1, u1);
  }
}
